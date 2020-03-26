package com.mr2.zaiko.zaiko2.ui.ItemDetailBrowser;

import androidx.annotation.NonNull;

import com.mr2.zaiko.zaiko2.domain.inhouse.equipment.Equipment;
import com.mr2.zaiko.zaiko2.domain.inhouse.orderSlip.OrderSlip;
import com.mr2.zaiko.zaiko2.domain.inhouse.storageLocation.StorageLocation;
import com.mr2.zaiko.zaiko2.domain.outside.commodity.Commodity;
import com.mr2.zaiko.zaiko2.domain.outside.company.Company;
import com.mr2.zaiko.zaiko2.domain.outside.product.Product;
import com.mr2.zaiko.zaiko2.ui.ImageViewer.ImageViewerResource;

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
    @NonNull
    private final List<OrderSlip> orderSlipList;
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
                                     @NonNull List<OrderSlip> orderSlipList,
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
        this.orderSlipList = orderSlipList;
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

    @NonNull
    public List<OrderSlip> getOrderSlipList() {
        return orderSlipList;
    }

    @NonNull
    public List<StorageLocation> getStorageLocationList() {
        return storageLocationList;
    }

    public ImageViewerResource getImageResource(){
        return new ImageViewerResource(equipment.photos(), filesAbstractPath);
    }
}
