package com.mr2.zaiko.xOld.Domain.Image;

import androidx.annotation.NonNull;

import com.mr2.zaiko.xOld.Domain.Product.Identity;

import java.util.List;

/**
 * 写真のアドレスのコレクション。最大10個までのアドレスを保持する。
 * そのうち一つを主写真(PrimaryImage)とする。
 * 【考え中】アドレスはローカル限定のつもりだけどリモートから引っ張ってくる可能性あるなら区分かなんか必要になる？
 */
public interface ImageCollection {
    ImageCollection of(Identity productIdentity);

    void addImage(@NonNull Image image); //NN UQ 1-10個
    void changePrimaryImage(int imageList_index); //
    void removeImage(int imageList_index); //

    Identity getIdentity();
    Image getPrimaryImage();
    Image getImage(int imageIdentity);
    List<Image> getList();
    int getSize();
}
