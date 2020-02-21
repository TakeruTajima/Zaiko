package com.mr2.zaiko.zaiko2.domain.common;

import java.util.Objects;
import java.util.UUID;

public abstract class Identity {
    private final String uuid;

    protected Identity(){
        this.uuid = UUID.randomUUID().toString();
    }

    protected Identity(String uuid){
        this.uuid = uuid;
    }

    public String id() {
        return uuid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Identity identity = (Identity) o;
        return Objects.equals(uuid, identity.uuid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid);
    }
}
