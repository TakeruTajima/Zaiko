package com.mr2.zaiko.domain.inhouse.orderSlip;

import com.mr2.zaiko.domain.inhouse.user.UserId;

import java.util.List;

public interface OrderSlipRepository {
    //発注票を保存する
    boolean save(OrderSlip orderSlip);
    //発注票を取得する
    //発注票を検索する
    OrderSlip get(OrderSlipId id);
    List<OrderSlip> findUncompleted();
    List<OrderSlip> getAll();
    List<OrderSlip> findUncompletedByUser(UserId userId);
    //発注票を削除する
    void remove(OrderSlip orderSlip);
}