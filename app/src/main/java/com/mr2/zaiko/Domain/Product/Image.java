package com.mr2.zaiko.Domain.Product;

import java.util.List;

public interface Image {
    void addImage(String newImageAddress); //NN UQ 0-10個 引数とメンバはStringでいいの？image_id？
    void changePrimaryImage(int imageList_index); //1以上　存在判定 todo:indexてこんな風に使えたっけ？
    void removeImage(int image_index); //1以上　存在判定

    int getIndexPrimaryImage();
    List<String> getImageAddressList();
}
