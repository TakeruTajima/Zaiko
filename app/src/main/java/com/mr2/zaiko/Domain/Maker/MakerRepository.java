package com.mr2.zaiko.Domain.Maker;

import androidx.annotation.Nullable;

import java.util.List;

public interface MakerRepository {
    //findById,findByCode,findAllByName,
    //create,update,deactivate,activate,

    /**
     * メーカーを登録
     * @param name メーカー名
     * @return メーカー
     * @throws IllegalArgumentException
     * メーカー名が規格外の場合
     */
    Maker register(String name) throws IllegalArgumentException;

    /**
     * メーカー情報を更新
     * @param maker メーカー
     * @return 再読み出ししたメーカー、更新できなかった場合はNull？
     * @throws IllegalArgumentException
     * メーカーが未登録の場合
     */
    Maker update(Maker maker) throws IllegalArgumentException;

    /**
     * メーカーを無効化
     * @param maker 有効なメーカー
     * @throws IllegalArgumentException
     * メーカーが未登録の場合、メーカーが既に無効な場合
     */
    void deactivate(Maker maker) throws IllegalArgumentException;

    /**
     * メーカーを有効化
     * @param code メーカーコード
     * @throws IllegalArgumentException
     * メーカーが未登録の場合、メーカーが既に有効な場合
     */
    void activate(Code code) throws IllegalArgumentException;

    /**
     * メーカーコードからメーカーを取得
     * @param code メーカーコード
     * @return メーカー、見つからない場合はNull
     */
    @Nullable Maker findByCode(Code code);

    /**
     * メーカー名の部分一致でメーカーを検索
     * @param name メーカー名
     * @return 部分一致、見つからない場合は0件のList
     */
    List<Maker> partialMatchByName(String name);

    /**
     * 全てのメーカーを取得
     * @return 有効なすべてのメーカー
     */
    List<Maker> getList();

    /**
     * 無効化されたメーカーを取得
     * @return 無効なすべてのメーカー
     */
    List<Maker> getInactive();
}
