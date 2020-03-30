package com.mr2.zaiko.zaiko2.domain.inhouse.orderSlip;

import com.mr2.zaiko.zaiko2.domain.common.Entity;
import com.mr2.zaiko.zaiko2.domain.inhouse.user.UserId;
import com.mr2.zaiko.zaiko2.domain.outside.commodity.CommodityId;
import com.mr2.zaiko.zaiko2.domain.outside.company.CompanyId;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

public class OrderSlipRewrite extends Entity {
    private OrderSlipId id;
    private UserId userId;
    private CompanyId sellerId;
    private List<OrderSlipItem> items;
    private ZonedDateTime createdAt;
    private ZonedDateTime orderAt;
    private ZonedDateTime completedAt;

    public OrderSlipRewrite(int unmutatedVersion) {
        super(unmutatedVersion); //TODO:途中
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
//        public CommodityId commodityId(){ return commodityId; }
    }
}
