package com.mr2.zaiko.domain.inhouse.orderSlip;

import com.mr2.zaiko.domain.inhouse.user.UserId;
import com.mr2.zaiko.domain.outside.commodity.CommodityId;

import org.junit.Before;
import org.junit.Test;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class OrderSlipTest {
    private OrderSlip orderSlip;
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss.SSS");
    private List<BuyRequest> requests = new ArrayList<>();
    private CommodityId aCommodity = new CommodityId();
    private CommodityId bCommodity = new CommodityId();
    private CommodityId cCommodity = new CommodityId();
    private UserId aUser = new UserId();
    private UserId bUser = new UserId();

    private synchronized void takeYourTime(){
        try{
            wait(1);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    @Before
    public void setUp() throws Exception {
        orderSlip = new OrderSlip(aUser);
        requests.add(new BuyRequest(aCommodity, 1, aUser, "備考1"));
        takeYourTime();
        requests.add(new BuyRequest(bCommodity, 2, aUser, "備考2"));
        takeYourTime();
        requests.add(new BuyRequest(cCommodity, 3, aUser, "備考3"));
        for (BuyRequest r : requests){
            orderSlip.addRequest(r);
        }
    }

//    @Test
//    public void test1() {
//        ZonedDateTime zonedDateTime = orderSlip.updatedAt();
//        try {
//            System.out.println("1:" + zonedDateTime.format(formatter));
//        }catch (NullPointerException e){ System.out.println("1:catchNPE");}
//        zonedDateTime = ZonedDateTime.now();
//        System.out.println("2:" + zonedDateTime.format(formatter));
//        try {
//            System.out.println("3:" + orderSlip.updatedAt().format(formatter));
//        }catch (NullPointerException e){ System.out.println("3:catchNPE");}
//    }

    @Test
    public void addRequest() {
        CommodityId eCommodity = new CommodityId();
        BuyRequest r = new BuyRequest(eCommodity, 1, aUser, "追加");
        try {
            orderSlip.addRequest(r);
            assertEquals(orderSlip.requests().get(3), r);
        }catch (IllegalArgumentException e){
            fail(e.getMessage());
        }

        try {
            orderSlip.addRequest(r);
            fail();
        }catch (IllegalArgumentException e){
            assertTrue(true);
        }
    }

    @Test
    public void replaceRequest() {
        try {
            orderSlip.replaceRequest(new BuyRequest(aCommodity, 10, aUser, "更新"));
            assertEquals(orderSlip.requests().get(2).commodityId(), aCommodity);
            assertEquals(orderSlip.requests().get(2).quantity(), 10);
        }catch (IllegalArgumentException e){
            fail(e.getMessage());
        }
        try{
            orderSlip.replaceRequest(new BuyRequest(new CommodityId(), 1, aUser, "新規"));
            fail();
        }catch (IllegalArgumentException e){
            assertTrue(true);
        }
    }

    @Test
    public void removeRequest() {
        try{
            orderSlip.removeRequest(new BuyRequest(new CommodityId(), 1, aUser, "新規"));
            fail();
        }catch (IllegalArgumentException e){
            assertTrue(true);
        }
    }

    @Test
    public void order() {
        orderSlip.order();
        assertTrue("発注済みになっていませんね…", orderSlip.isOrdered());
        List<Order> list = orderSlip.orders();
        if (list.size() != requests.size()) fail();
        for (int i = 0; requests.size() > i; i++){
            Order o = list.get(i);
            BuyRequest r = requests.get(i);
            assertEquals("IDが違いますね…", o.commodityId(), r.commodityId());
        }
    }

    @Test
    public void orderedCommodityArrived() {
        orderSlip.order();
        //*** This test assumes 'order()' successes. ***//
        List<Order> orders = orderSlip.orders();
        for (Order o : orders) {
            orderSlip.orderedCommodityArrived(bUser, o, o.quantity());
        }
        List<Arrival> arrivals = orderSlip.arrivals();
        assertEquals("数が合いませんね…", orders.size(), arrivals.size());
        for (int i = 0; orders.size() > i; i++){
            Order o = orders.get(i);
            Arrival a = arrivals.get(i);
            assertEquals("IDが違いますね…", o.commodityId(), a.commodityId());
        }
    }

    @Test
    public void close() {
        orderSlip.close();
        assertTrue("締め切ったはずでしたが…", orderSlip.isClosed());
        try{
            orderSlip.addRequest(new BuyRequest(new CommodityId(),1,aUser,"追加1"));
            fail("追加できてしまいましたね…");
        }catch (IllegalStateException e){System.out.println(e.getMessage());}
        try{
            orderSlip.replaceRequest(new BuyRequest(aCommodity, 3, aUser, "変更1"));
            fail("変更できてしまいましたね…");
        }catch (IllegalStateException e){System.out.println(e.getMessage());}
        try{
            orderSlip.removeRequest(requests.get(1));
            fail("除外できてしまいましたね…");
        }catch (IllegalStateException e){System.out.println(e.getMessage());}
        try{
            orderSlip.order();
            fail("発注できてしまいましたね…");
        }catch (IllegalStateException e){System.out.println(e.getMessage());}
        try{
            List<Order> orders = new ArrayList<>();
            orders.add(new Order(aCommodity, 1, aUser, ""));
            for (Order o : orders){
                orderSlip.orderedCommodityArrived(bUser, o, o.quantity());
            }
            fail("入荷できてしまいましたね…(|)");
        }catch (IllegalStateException e){System.out.println(e.getMessage());}
    }

    @Test
    public void checkArrivalQuantity() {
//        for (BuyRequest r : requests){
//            System.out.println(r.note());
//        }
//        requests.sort(OrderSlipComparatorService.getBuyRequestComparator());
//        for (BuyRequest r : requests){
//            System.out.println(r.note());
//        }
//        BuyRequest first = requests.get(0);
//        BuyRequest second = requests.get(1);
//        BuyRequest third = requests.get(2);
//        System.out.println(first.note() + " " + first.createdAt().format(formatter));
//        System.out.println(second.note() + " " + second.createdAt().format(formatter));
//        System.out.println(third.note() + " " + third.createdAt().format(formatter));
    }
}