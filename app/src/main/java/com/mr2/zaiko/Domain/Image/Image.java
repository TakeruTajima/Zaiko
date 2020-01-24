package com.mr2.zaiko.Domain.Image;

import androidx.annotation.NonNull;

import com.mr2.zaiko.Domain.Product.Identity;

public interface Image {
    public Image of(int _id, @NonNull Identity productIdentity, @NonNull String address);

    public int getIdentity();
    public Identity getProductIdentity();
    public String getAddress();
}
