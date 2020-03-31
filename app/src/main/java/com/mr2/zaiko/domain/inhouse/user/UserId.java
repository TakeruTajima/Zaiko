package com.mr2.zaiko.domain.inhouse.user;

import com.mr2.zaiko.domain.common.Identity;

public class UserId extends Identity {
    public UserId() {
        super();
    }

    protected UserId(String uuid) {
        super(uuid);
    }


}
