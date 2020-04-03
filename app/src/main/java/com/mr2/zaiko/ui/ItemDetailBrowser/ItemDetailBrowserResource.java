package com.mr2.zaiko.ui.ItemDetailBrowser;

import androidx.annotation.NonNull;

import com.mr2.zaiko.domain.inhouse.equipment.Equipment;
import com.mr2.zaiko.domain.inhouse.equipment.Keyword;
import com.mr2.zaiko.domain.inhouse.equipment.Photo;
import com.mr2.zaiko.domain.inhouse.storageLocation.StorageLocation;
import com.mr2.zaiko.domain.outside.commodity.Commodity;
import com.mr2.zaiko.domain.outside.company.Company;
import com.mr2.zaiko.domain.outside.product.Model;
import com.mr2.zaiko.domain.outside.product.Name;
import com.mr2.zaiko.domain.outside.product.Price;
import com.mr2.zaiko.domain.outside.product.Product;
import com.mr2.zaiko.domain.outside.product.Unit;
import com.mr2.zaiko.ui.ImageViewer.ImageViewerResource;

import java.util.ArrayList;
import java.util.List;

public class ItemDetailBrowserResource {
    @NonNull
    private final Product product;
    @NonNull
    private final Company maker;
    @NonNull
    private final Equipment equipment;
    @NonNull
    private final Commodity commodity;
    @NonNull
    private final Company seller;
    @NonNull
    private final String filesAbstractPath;
//    @NonNull
//    private final List<OrderSlip> orderSlipList;
    @NonNull
    private final List<StorageLocation> storageLocationList;
    // ImageViewer呼び出し時に読み出す？？
    // ->ImageViewerの責務は「渡されたResourceを画像として表示する」だから読出しはやめといたほうが
    // ->TODO: 呼び出し側(ItemDetailBrowserPresenter <- ItemBrowsingApplicationService(UseCase?) <- 各Repository)
    //  で読出してImageViewerに渡す
    //
    //  入荷待ちメッセージは？ ->presenterで作る


    public ItemDetailBrowserResource(@NonNull Product product,
                                     @NonNull Company maker,
                                     @NonNull Equipment equipment,
                                     @NonNull Commodity commodity,
                                     @NonNull Company seller,
//                                     @NonNull List<OrderSlip> orderSlipList,
                                     @NonNull List<StorageLocation> storageLocationList,
                                     @NonNull String filesAbstractPath) {
        if(!product.equals(equipment)) throw new IllegalArgumentException("製品と備品のIDが一致しません。");
        if (!product.companyId().equals(maker.id())) throw new IllegalArgumentException("製品と製造会社のIDが一致しません");
        if (!commodity.companyId().equals(seller.id())) throw new IllegalArgumentException("商品と販売会社のIDが一致しません");

        this.product = product;
        this.maker = maker;
        this.equipment = equipment;
        this.commodity = commodity;
        this.seller = seller;
//        this.orderSlipList = orderSlipList;
        this.storageLocationList = storageLocationList;
        this.filesAbstractPath = filesAbstractPath;
    }

    @NonNull
    public Product getProduct() {
        return product;
    }

    @NonNull
    public Company getMaker() {
        return maker;
    }

    @NonNull
    public Equipment getEquipment() {
        return equipment;
    }

    @NonNull
    public Commodity getCommodity() {
        return commodity;
    }

    @NonNull
    public Company getSeller() {
        return seller;
    }

//    @NonNull
//    public List<OrderSlip> getOrderSlipList() {
//        return Collections.unmodifiableList(orderSlipList);
//    }

    @NonNull
    public List<StorageLocation> getStorageLocationList() {
        return storageLocationList;
    }

    public ImageViewerResource getImageResource(){
        return new ImageViewerResource(equipment.photos(), filesAbstractPath);
    }


    public static ItemDetailBrowserResource getTestResource(){
        Company testMaker = new Company("テストメーカー");
        Company testSeller = new Company("テスト商社");
        Product testProduct = testMaker.createProduct(new Model("test-model"), new Name("テスト製品名"), new Unit("製造単位"), new Price(100, "円"));
        Equipment testEquipment = new Equipment(testProduct, new Name("テスト備品名"), new Unit("管理単位"), new Price(110, "円"));
        testEquipment.addPhoto(new Photo("20200309033935.jpg"));
        testEquipment.addPhoto(new Photo("20200309033935.jpg"));
        testEquipment.addKeyword(new Keyword("ジャンル1"));
        testEquipment.addKeyword(new Keyword("タグ"));
        Commodity testCommodity = testSeller.registerCommodity(testProduct, new Name("テスト商品名"), new Unit("販売単位"), new Price(111, "円"));
//        OrderSlip testOrderSlip = new OrderSlip(new UserId());
//        testOrderSlip
        List<StorageLocation> testStorageLocationList = new ArrayList<>();
        StorageLocation testStorageLocation = new StorageLocation(testEquipment.equipmentId(), "AA010101", StorageLocation.Condition.BrandNew);
        testStorageLocationList.add(testStorageLocation);
        return new ItemDetailBrowserResource(
                testProduct,
                testMaker,
                testEquipment,
                testCommodity,
                testSeller,
                testStorageLocationList,
                "/data/user/0/com.mr2.zaiko/files/"
        );
    }
}
