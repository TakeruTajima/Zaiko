package com.mr2.zaiko.infrastructure;

import android.content.Context;

import androidx.annotation.NonNull;

import com.mr2.zaiko.domain.common.Entity;
import com.mr2.zaiko.domain.inhouse.orderSlip.OrderSlip;
import com.mr2.zaiko.domain.inhouse.orderSlip.OrderSlipId;
import com.mr2.zaiko.domain.inhouse.orderSlip.OrderSlipRepository;
import com.mr2.zaiko.domain.inhouse.user.UserId;

import java.time.format.DateTimeFormatter;
import java.util.List;

class OrderSlipRepositoryImpl implements OrderSlipRepository {
    private DBAdapter adapter;
    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss");

    public OrderSlipRepositoryImpl(@NonNull Context context) {
        this.adapter = new DBAdapter(context);
    }

    @Override
    public boolean save(OrderSlip orderSlip) {
//        int ver = check(orderSlip).unmutatedVersion();
//        adapter.beginTransaction();
//
//        List<BuyRequest> requests = orderSlip.requests();
//        for (BuyRequest request: requests){
//            ContentValues values = new ContentValues();
//            values.put("user_id", request.userId().id());
//            values.put("commodity_id", request.commodityId().id());
//            values.put("quantity", request.quantity());
//            values.put("created_at", request.createdAt().format(formatter));
//            values.put("note", request.note());
//            String where = " user_id = " + request.userId().id() + " AND " +
//                    " commodity_id = " + request.commodityId().id() + " ";
//            Cursor c = adapter.findAllRecords("requests", where);
//            if (0 == c.getCount()){
//                adapter.insertRecords("requests", values);
//            }else {
//                adapter.updateRecords("requests", values, where);
//            }
//        }
//        List<Order> orders = orderSlip.orders();
//        for (Order order: orders){
//            ContentValues values = new ContentValues();
//            values.put("user_id", order.userId().id());
//            values.put("commodity_id", order.commodityId().id());
//            values.put("quantity", order.quantity());
//            values.put("created_at", order.createdAt().format(formatter));
//            values.put("note", order.note());
//            String where = " user_id = " + order.userId() + " AND " +
//                    " commocity_id = " + order.commodityId().id() + " ";
//            Cursor c =
//        }
        return false;
    }

    @Override
    public OrderSlip get(OrderSlipId id) {
        return null;
    }

    @Override
    public List<OrderSlip> findUncompleted() {
        return null;
    }

    @Override
    public List<OrderSlip> getAll() {
        return null;
    }

    @Override
    public List<OrderSlip> findUncompletedByUser(UserId userId) {
        return null;
    }

    @Override
    public void remove(OrderSlip orderSlip) {

    }

    private Entity check(Object o){
        if (!(o instanceof Entity)) throw new IllegalArgumentException("これはEntityではありません。");
        return  (Entity) o;
    }
}
