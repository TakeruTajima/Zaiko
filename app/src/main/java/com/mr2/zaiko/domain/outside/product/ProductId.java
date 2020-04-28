package com.mr2.zaiko.domain.outside.product;

import com.mr2.zaiko.domain.common.Identity;

public class ProductId extends Identity {
    protected ProductId() {
        super();
    }

    public ProductId(String uuid) {
        super(uuid);
    }
}
