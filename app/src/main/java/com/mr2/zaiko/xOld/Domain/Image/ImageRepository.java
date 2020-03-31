package com.mr2.zaiko.xOld.Domain.Image;

import androidx.annotation.NonNull;

import com.mr2.zaiko.xOld.Domain.Base.SimpleCrudRepository;
import com.mr2.zaiko.xOld.Domain.Product.Identity;

public interface ImageRepository extends SimpleCrudRepository<Image, Integer> {
    /**
     * Imageの存在判定をします。
     * @param productIdentity 部品ID
     * @return Imageがひとつでも存在するならTrueを返します。
     */
    boolean existsByProduct(@NonNull Identity productIdentity);

    /**
     * Imageのコレクションを返します。
     * @param productIdentity 部品ID
     * @return 画像のコレクション
     */
    ImageCollection findAllByProduct(@NonNull Identity productIdentity);

}
