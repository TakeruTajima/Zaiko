package com.mr2.zaiko.xOld.Domain.Catalogue;

import androidx.annotation.NonNull;

import com.mr2.zaiko.xOld.Domain.Product.Product;
import com.mr2.zaiko.xOld.Domain.Seller.Seller;

import java.util.List;

public interface CatalogRepository {
    /**
     * カタログの登録を行います。
     * @param product　登録する部品
     * @param seller　登録する商社
     * @param name　商社から見た品名
     * @return 登録したカタログ
     * @throws IllegalArgumentException 品名が規格外, ProductかSellerが存在しない
     */
    Catalog register(@NonNull Product product, @NonNull Seller seller, @NonNull String name) throws IllegalArgumentException;

    /**
     * カタログの存在確認
     * @param product　部品
     * @param seller　商社
     * @return Boolean
     * @throws IllegalArgumentException ProductかSellerが存在していない
     */
    boolean exists(@NonNull Product product, @NonNull Seller seller) throws IllegalArgumentException;

    /**
     * Productからカタログを取得
     * @param product　
     * @return Catalogリスト
     * @throws IllegalArgumentException Productが存在しない
     */
    List<Catalog> get(Product product) throws IllegalArgumentException;

    /**
     * ProductのCatalogの数を返します
     * @param product 部品
     * @return Catalogの件数
     * @throws IllegalArgumentException Productが存在しない
     */
    int count(Product product) throws IllegalArgumentException;
    List<Catalog> get(Seller seller) throws IllegalArgumentException;
    int count(Seller seller) throws IllegalArgumentException;
    List<Catalog> getInactive(Product product, Seller seller) throws IllegalArgumentException;
    int countInactive(Product product, Seller seller) throws IllegalArgumentException;
    Catalog update(Catalog catalog) throws IllegalArgumentException;
    void deactivate(Catalog catalog) throws IllegalArgumentException;
    Catalog activate(Product product, Seller seller) throws IllegalArgumentException;
}
