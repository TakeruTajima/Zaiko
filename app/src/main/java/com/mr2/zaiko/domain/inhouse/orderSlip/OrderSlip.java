package com.mr2.zaiko.domain.inhouse.orderSlip;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.mr2.zaiko.domain.common.Entity;
import com.mr2.zaiko.domain.inhouse.user.UserId;
import com.mr2.zaiko.domain.outside.commodity.CommodityId;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class OrderSlip extends Entity {
    @NonNull private OrderSlipId id;
    @NonNull private final UserId userId;//ユーザ
    @NonNull private List<BuyRequest> requests = new ArrayList<>();
    @NonNull private List<Order> orders = new ArrayList<>();
    @NonNull private List<Arrival> arrivals = new ArrayList<>();
    @NonNull private final ZonedDateTime createdAt;//登録日時
    @Nullable private ZonedDateTime updatedAt;//更新日時
    @Nullable private ZonedDateTime completedAt;//完了日時
    private boolean ordered = false;//発注済み

    public OrderSlip(@NonNull UserId userId) {
        super(INITIAL_UNMUTATED_VERSION);
        this.id = new OrderSlipId();
        this.userId = userId;
        this.createdAt = ZonedDateTime.now();
    }

    OrderSlip(int unmutatedVersion, @NonNull OrderSlipId orderSlipId, @NonNull UserId userId, @NonNull List<BuyRequest> requests, @NonNull List<Order> orders, @NonNull List<Arrival> arrivals, @NonNull ZonedDateTime createdAt, @Nullable ZonedDateTime updatedAt, @Nullable ZonedDateTime completedAt, boolean ordered) {
        super(unmutatedVersion);
        this.id = orderSlipId;
        this.userId = userId;
        this.requests = requests;
        this.orders = orders;
        this.arrivals = arrivals;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.completedAt = completedAt;
        this.ordered = ordered;
    }

    @NonNull
    public OrderSlipId id(){ return id; }

    @NonNull
    public UserId userId() {
        return userId;
    }

    @NonNull
    public List<BuyRequest> requests() {
        return Collections.unmodifiableList(requests);
    }

    @NonNull
    public List<Order> orders() {
        return Collections.unmodifiableList(orders);
    }

    @NonNull
    public List<Arrival> arrivals() {
        return Collections.unmodifiableList(arrivals);
    }

    @NonNull
    public ZonedDateTime createdAt() {
        return createdAt;
    }

    @Nullable
    public ZonedDateTime updatedAt() {
        return updatedAt;
    }

    @Nullable
    public ZonedDateTime completedAt() {
        return completedAt;
    }

    public boolean isOrdered() {
        return ordered;
    }
//------------------------------------

    //依頼を追加する
    @Deprecated
    public void addRequest(@NonNull BuyRequest request){
        assertStateFalse(isClosed(), "既に締め切られているため依頼を追加することができません。");
        assertStateFalse(ordered, "既に発注されているため依頼を追加することができません。");
        CommodityId id = request.commodityId();
        for (BuyRequest r : requests){
            assertArgumentNotEquals(id, r.commodityId(), "商品は重複しています。");
        }
        requests.add(request);

        updatedAt = ZonedDateTime.now();
    }

    public void addRequest(CommodityId commodityId, int quantity, UserId userId, String note) {
        assertStateFalse(isClosed(), "既に締め切られているため依頼を追加することができません。");
        assertStateFalse(ordered, "既に発注されているため依頼を追加することができません。");
        for (BuyRequest r : requests){
            assertArgumentNotEquals(commodityId, r.commodityId(), "商品は重複しています。");
        }

        requests.add(new BuyRequest(commodityId, quantity, userId, note));
        updatedAt = ZonedDateTime.now();
    }

    //依頼を変更する
    @Deprecated
    public void replaceRequest(@NonNull BuyRequest request){
        assertStateFalse(isClosed(), "既に締め切られているため依頼を変更することができません。");
        assertStateFalse(ordered, "既に発注されているため依頼を変更することができません。");
        CommodityId id = request.commodityId();
        for (BuyRequest r : requests){
            if (id.equals(r.commodityId())) {
                requests.remove(r);
                requests.add(request);
                updatedAt = ZonedDateTime.now();
                return;
            }
        }
        throw new IllegalArgumentException("その商品は登録されていません。");
    }

    public void replaceRequest(BuyRequest request, int quantity, String note){
        assertStateFalse(isClosed(), "既に締め切られているため依頼を変更することができません。");
        assertStateFalse(ordered, "既に発注されているため依頼を変更することができません。");
        for (BuyRequest r: requests){
            if (r == request){
                requests.remove(r);
                requests.add(new BuyRequest(request.commodityId(), quantity, request.userId(), note));
                updatedAt = ZonedDateTime.now();
                return;
            }
        }
        throw new IllegalArgumentException("その商品は登録されていません。");
    }

    //依頼を却下/取り消す
    public void removeRequest(BuyRequest request){
        assertStateFalse(isClosed(), "既に締め切られているため依頼を取り消すことができません。");
        assertStateFalse(ordered, "既に発注されているため依頼を取り消すことができません。");
        CommodityId id = request.commodityId();
        for (BuyRequest r : requests){
            if (id.equals(r.commodityId())){
                requests.remove(r); //TODO:とりあえずリムーブしとくけど消したこと分かるようにしないよね
                updatedAt = ZonedDateTime.now();
                return;
            }
        }
        throw new IllegalArgumentException("その商品は登録されていません。");
    }
    //発注する
    public void order(){
        assertStateFalse(isClosed(), "既に締め切られているため発注することができません。");
        assertStateFalse(ordered, "既に発注されています。");
        //TODO:CSVファイル出力なりメール飛ばすなり
        for (BuyRequest r : requests){
            orders.add(new Order(
                    r.commodityId(),
                    r.quantity(),
                    r.userId(),
                    ZonedDateTime.now(),
                    r.note()));
        }
        ordered = true;
        updatedAt = ZonedDateTime.now();
    }
    //商品を入荷する
    public void orderedCommodityArrived(UserId loginUser, Order order, int quantity){
        assertStateFalse(isClosed(), "既に締め切られているため入荷することができません。");
        assertStateTrue(ordered, "発注されていないため入荷することができません。");
        CommodityId id = order.commodityId();
        for (Order o : orders){
            if (id.equals(o.commodityId())){
                arrivals.add(new Arrival(
                        o.commodityId(),
                        quantity,
                        loginUser,
                        ZonedDateTime.now(),
                        o.note()));
                return;
            }
        }
        throw new IllegalArgumentException("その商品は発注されていません。");
    }
    //発注票を締め切る
    public void close(){
        completedAt = ZonedDateTime.now();
    }

    public boolean isClosed(){
        return null != completedAt;
    }

    //入荷完了判定
    public boolean checkArrivalQuantity(Order order){
        assertStateTrue(ordered, "発注されていません。"); //TODO: 発注なしで納入することあるよ
        CommodityId id = order.commodityId();
        int arrivalQuantity = 0;
        for (Arrival a : arrivals){
            if (id.equals(a.commodityId()))
                arrivalQuantity += a.quantity();
        }
        return order.quantity() <= arrivalQuantity;
    }
}
