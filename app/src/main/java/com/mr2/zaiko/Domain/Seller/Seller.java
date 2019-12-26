package com.mr2.zaiko.Domain.Seller;

import androidx.annotation.NonNull;

import com.mr2.zaiko.Domain.Maker.Code;
import com.mr2.zaiko.Domain.Maker.Maker;

public class Seller extends Maker {
    public Seller(@NonNull Code companyCode, @NonNull String companyName) {
        super(companyCode, companyName);
    }
}
