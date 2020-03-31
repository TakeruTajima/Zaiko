package com.mr2.zaiko.domain.inhouse.orderSlip;

import com.mr2.zaiko.domain.common.Entity;
import com.mr2.zaiko.domain.inhouse.user.UserId;
import com.mr2.zaiko.domain.outside.commodity.CommodityId;
import com.mr2.zaiko.domain.outside.company.CompanyId;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

public class OrderSlipRewrite extends Entity {
    public static final int STATE_REQUESTED = 1;
    public static final int STATE_WAITING_DELIVERY = 2;
    public static final int STATE_DELIVERY_COMPLETED = 3;

    private OrderSlipId id;
    private UserId userId;
    private CompanyId sellerId;
    private List<OrderSlipItem> items;
    private ZonedDateTime createdAt;
    private ZonedDateTime orderAt;
    private ZonedDateTime completedAt;

    public OrderSlipRewrite(int unmutatedVersion) {
        super(unmutatedVersion); //TODO: Refactoring途中
    }

    public OrderSlipId id(){ return id; }
    public UserId userId(){ return userId; }
    public CompanyId sellerId(){ return sellerId; }
    public int itemSize(){ return items.size(); }
    public ZonedDateTime createdAt(){ return completedAt; }
    public ZonedDateTime orderAt(){ return orderAt; }
    public ZonedDateTime completedAt(){ return completedAt; }

    public int itemState(int position){
        if (!(itemSize() > position)) return -1;
        OrderSlipItem item = items.get(position);
        if (item.isArrivalCompleted()) return STATE_DELIVERY_COMPLETED; //納入済
        if (item.isOrder()) return STATE_WAITING_DELIVERY;              //納入待ち
        if (item.isRequested()) return STATE_REQUESTED;                 //リクエスト中
        return -1;
    }
    public int itemState(CommodityId commodityId){
        if (!isIncluded(commodityId)) return -1;
        for (int i = 0; items.size() > i; i++){
            OrderSlipItem o = items.get(i);
            if (o.commodityId == commodityId) return itemState(i);
        }
        return -1;
    }

    public boolean isIncluded(CommodityId commodityId){
        for (OrderSlipItem o: items){
            if (commodityId.equals(o.commodityId)) return true;
        }
        return false;
    }

    public void request(CommodityId commodityId, int quantity, UserId userId, String note){
        if (!isIncluded(commodityId)) {
            OrderSlipItem item = new OrderSlipItem(commodityId);
            item.buyRequest = new BuyRequest(commodityId, quantity, userId, note);
            return;
        }
        for (OrderSlipItem o: items){
            if (commodityId.equals(o.commodityId))
                o.buyRequest = new BuyRequest(commodityId, quantity, userId, note);
        }
    }
    public void order(CommodityId commodityId, int quantity, UserId userId, String note){
        if (!isIncluded(commodityId)) {
            OrderSlipItem item = new OrderSlipItem(commodityId);
            item.order = new Order(commodityId, quantity, userId, note);
            return;
        }
        for (OrderSlipItem o: items){
            if (commodityId.equals(o.commodityId))
                o.order = new Order(commodityId, quantity, userId, note);
        }
    }
    public void arrival(CommodityId commodityId, int quantity, UserId userId, String note){
        if (!isIncluded(commodityId)) {
            OrderSlipItem item = new OrderSlipItem(commodityId);
            item.arrivalList.add(new Arrival(commodityId, quantity, userId, note));
            return;
        }
        for (OrderSlipItem o: items){
            if (commodityId.equals(o.commodityId))
                assertArgumentFalse(o.isArrivalCompleted(), "これ以上納入できません。");
                o.arrivalList.add(new Arrival(commodityId, quantity, userId, note));
        }
    }



    private class OrderSlipItem{
        private CommodityId commodityId;
        private BuyRequest buyRequest;
        private Order order;
        private List<Arrival> arrivalList;

        public OrderSlipItem(CommodityId commodityId){
            this.commodityId = commodityId;
            arrivalList = new ArrayList<>();
        }

        public boolean isRequested(){ return null != buyRequest; }
        public boolean isOrder(){ return null != order; }
        public boolean isArrivalCompleted(){
            if (0 == expectedArrivalQuantity()) return false;
            return totalArrivalsQuantity() >= expectedArrivalQuantity();
        }
        public int expectedArrivalQuantity(){
            if (null == order && null == buyRequest) return 0;
            if (null == order) return buyRequest.quantity();
            return order.quantity();
        }
        public int totalArrivalsQuantity(){
            int total = 0;
            for (Arrival a: arrivalList){
                total += a.quantity();
            }
            return total;
        }
    }
}
