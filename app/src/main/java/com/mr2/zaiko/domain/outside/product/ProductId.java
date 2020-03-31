package com.mr2.zaiko.domain.outside.product;

import com.mr2.zaiko.domain.common.Identity;

public class ProductId extends Identity {
    protected ProductId() {
        super();
    }

    protected ProductId(String uuid) {
        super(uuid);
    }
}
