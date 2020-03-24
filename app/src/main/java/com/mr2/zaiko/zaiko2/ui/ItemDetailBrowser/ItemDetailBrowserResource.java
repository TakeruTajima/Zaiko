package com.mr2.zaiko.zaiko2.ui.ItemDetailBrowser;

import androidx.annotation.NonNull;

import com.mr2.zaiko.zaiko2.domain.inhouse.equipment.Equipment;
import com.mr2.zaiko.zaiko2.domain.inhouse.equipment.Keyword;
import com.mr2.zaiko.zaiko2.domain.outside.commodity.Commodity;
import com.mr2.zaiko.zaiko2.domain.outside.company.Company;
import com.mr2.zaiko.zaiko2.domain.outside.product.Product;
import com.mr2.zaiko.zaiko2.ui.ImageViewer.ImageViewerResource;

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
    // ImageViewer呼び出し時に読み出す？？
    // ->ImageViewerの責務は「渡されたResourceを画像として表示する」だから読出しはやめといたほうが
    // ->TODO: 呼び出し側(ItemDetailBrowserPresenter <- ItemBrowsingApplicationService(UseCase?) <- 各Repository)
    //  で読出してImageViewerに渡す
    //
    //  入荷待ちメッセージは？


    public ItemDetailBrowserResource(@NonNull Product product,
                                     @NonNull Company maker,
                                     @NonNull Equipment equipment,
                                     @NonNull Commodity commodity,
                                     @NonNull Company seller,
                                     @NonNull String filesAbstractPath) {
        if(!product.equals(equipment)) throw new IllegalArgumentException("製品と備品のIDが一致しません。");
        if (!product.companyId().equals(maker.id())) throw new IllegalArgumentException("製品と製造会社のIDが一致しません");
        if (!commodity.companyId().equals(seller.id())) throw new IllegalArgumentException("商品と販売会社のIDが一致しません");

        this.product = product;
        this.maker = maker;
        this.equipment = equipment;
        this.commodity = commodity;
        this.seller = seller;
        this.filesAbstractPath = filesAbstractPath;
    }


    //メーカー名
    public String getMakerName(){
        return maker.name();
    }
    //型式
    public String getModel(){ return product.model().model(); }
    //管理品名
    public String getInHouseName(){ return equipment.name().name(); }
    //管理単価
    public String getInHousePrice(){ return equipment.price().price(); }
    //管理単位
    public String getInHouseUnit(){ return equipment.unit().unit(); }
    //製造品名
    public String getProductName(){ return product.name().name(); }
    //製造単価
    public String getProductPrice(){ return product.price().price(); }
    //製造単位
    public String getProductUnit(){ return product.unit().unit(); }
    //代表販売商社名
    public String getSellerName(){ return seller.name(); }
    //代表販売品名
    public String getCommodityName(){ return commodity.name().name(); }
    //代表販売単価
    public String getCommodityPrice(){ return commodity.price().price(); }
    //代表販売単位
    public String getCommodityUnit(){ return commodity.unit().unit(); }
    //タグ
    public List<String> getKeywordList(){
        List<String> keywordList = new ArrayList<>();
        for (Keyword k: equipment.keywordSet()){
            keywordList.add(k.word());
        }
        return keywordList;
    }
    //ImageResource
    public ImageViewerResource getImageResource(){
        return new ImageViewerResource(equipment.photos(), filesAbstractPath);
    }
}
