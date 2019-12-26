package com.mr2.zaiko.Domain.Product;

import com.mr2.zaiko.Domain.Base.SimpleCrudRepository;
import com.mr2.zaiko.Domain.Maker.Code;
import com.mr2.zaiko.Domain.Maker.Maker;
import com.mr2.zaiko.Domain.Seller.Seller;

import java.util.List;

interface ProductRepository extends SimpleCrudRepository<Product, Integer> {

    /**
     * 新規部品登録
     * @param code メーカーコード
     * @param model 型式
     * @param name 品名
     * @return 登録した部品
     * @throws IllegalArgumentException-
     * 型式が不正、品名が不正
     */
    Product register(Code code, String model, String name)
            throws IllegalArgumentException;

    /**
     * 部品基幹情報の更新
     * @param product 更新したい部品
     * @return 更新した部品
     * @throws IllegalArgumentException-不変項目が不正
     */
    Product update(Product product) throws IllegalArgumentException;

    void deactivate(Product product);

    void activate(int productIdentity);

    /**
     * 有効なすべての部品を取得
     * @return 全ての有効な部品、0件のList
     */
    List<Product> getList();

    /**
     * @return getList()の件数
     */
    long count();

    List<Product> getInactiveList();

    long countInactive();

    /**
     * メーカーの部品を検索
     * @param maker
     * @return
     */
    List<Product> findAllByMaker(Maker maker);

    long countByMaker(Maker maker);

    List<Product> findAllBySeller(Seller seller);

    long countBySeller(Seller seller);

    boolean existsModel(Maker maker, Model model);
}
