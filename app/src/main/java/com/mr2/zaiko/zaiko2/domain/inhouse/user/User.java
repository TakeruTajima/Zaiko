package com.mr2.zaiko.zaiko2.domain.inhouse.user;

import androidx.annotation.NonNull;

import com.mr2.zaiko.zaiko2.domain.common.Entity;

import java.util.Objects;

public class User extends Entity {
    @NonNull private final UserId id;
    @NonNull private final String nameCode;
    @NonNull private Name name;
    @NonNull private Authority authority;
    private boolean authenticated;

    public User(@NonNull String nameCode, @NonNull Name name, @NonNull Authority authority) {
        super(0);
        this.id = new UserId();
        this.nameCode = nameCode;
        this.name = name;
        this.authority = authority;
    }

    public User(int version, @NonNull UserId id, @NonNull String nameCode, @NonNull Name name, @NonNull Authority authority) {
        super(version);
        this.id = id;
        this.nameCode = nameCode;
        this.name = name;
        this.authority = authority;
    }

    public UserId id(){ return id; }

    public String nameCode(){ return nameCode; }

    public Name name(){ return name; }

    public void rename(Name name){ this.name = name; }

    public Authority authority(){ return authority; }

    public void authorize(Authority authority){ this.authority = authority; }

    public boolean isAuthenticated(){ return authenticated; }

    public void enable(){
        authenticated = true;
    }

    public void disable(){ authenticated = false; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return id.equals(user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
