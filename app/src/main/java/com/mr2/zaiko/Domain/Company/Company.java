package com.mr2.zaiko.Domain.Company;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.mr2.zaiko.Domain.CreatedDateTime;
import com.mr2.zaiko.Domain.DeletedDateTime;
import com.mr2.zaiko.Domain.Id;

import java.util.Objects;


public class Company {
    private Id _id;
    private CompanyName name;
    private Category category;
    private CreatedDateTime createdAt;
    private DeletedDateTime deletedAt;

    private Company(@NonNull Id _id){
        this._id = _id;
    }

    private Company(@NonNull CompanyName name, @NonNull Category category) {
        this._id = null;
        this.name = name;
        this.category = category;
        this.createdAt = null;
        this.deletedAt = null;
    }

    Company(@NonNull Id _id, @NonNull CompanyName name, @NonNull Category category, @NonNull CreatedDateTime createdAt, @Nullable DeletedDateTime deletedAt) {
        this._id = _id;
        this.name = name;
        this.category = category;
        this.createdAt = createdAt;
        this.deletedAt = deletedAt;
    }

    public static Company of(@NonNull CompanyName name, @NonNull Category category){
        return new Company(name, category);
    }

    public static Company of(@NonNull Id _id){
        return new Company(_id);
    }

    public Id get_id() { return _id; }

    public CompanyName getName() { return name; }

    public Category getCategory(){ return category;}

    public boolean isMaker() {
        return category.isMaker();
    }

    public boolean isSeller() {
        return category.isSeller();
    }

    public CreatedDateTime getCreatedAt() {return createdAt; }

    public DeletedDateTime getDeletedAt() {return deletedAt; }

    @NonNull
    @Override
    public String toString() {
        return "Company{" +
                "_id=" + _id +
                ", name=" + name +
                ", category=" + category +
                ", createdAt=" + createdAt +
                ", deletedAt=" + deletedAt +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Company company = (Company) o;
        return Objects.equals(_id, company._id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(_id);
    }
}

