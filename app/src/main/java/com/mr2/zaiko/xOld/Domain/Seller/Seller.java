package com.mr2.zaiko.xOld.Domain.Seller;

import androidx.annotation.NonNull;

import com.mr2.zaiko.xOld.Domain.Maker.Code;
import com.mr2.zaiko.xOld.Domain.Maker.Maker;

public class Seller extends Maker {
    public Seller(@NonNull Code companyCode, @NonNull String companyName) {
        super(companyCode, companyName);
    }
}
