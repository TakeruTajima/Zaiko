package com.mr2.zaiko.Domain.UnitType;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.mr2.zaiko.Domain.CreatedDateTime;
import com.mr2.zaiko.Domain.DeletedDateTime;
import com.mr2.zaiko.Domain.Id;

import java.util.Date;
import java.util.Objects;

public class UnitType {
    private final Id _id;
    private final UnitTypeName name;
    private final CreatedDateTime createdAt;
    private final DeletedDateTime deletedAt;

    private UnitType(@NonNull Id _id){
        this._id = _id;
        name = null;
        createdAt = null;
        deletedAt = null;
    }

    private UnitType(@NonNull UnitTypeName name) {
        this._id = null;
        this.name = name;
        this.createdAt = null;
        this.deletedAt = null;
    }

    UnitType(@NonNull Id _id, @NonNull UnitTypeName name, @NonNull CreatedDateTime createdAt, @Nullable DeletedDateTime deletedAt) {
        this._id = _id;
        this.name = name;
        this.createdAt = createdAt;
        this.deletedAt = deletedAt;
    }

    public static UnitType of(@NonNull UnitTypeName name){
        return new UnitType(name);
    }

    public static UnitType of(@NonNull Id _id){
        return new UnitType(_id);
    }

    public Id get_id() {return _id; }

    public UnitTypeName getName() {
        return name;
    }

    public CreatedDateTime getCreatedAt() {return createdAt;}

    public DeletedDateTime getDeletedAt() {return deletedAt;}

    @Override
    public String toString() {
        return "UnitType{" +
                "_id=" + _id +
                ", name=" + name +
                ", createdAt=" + createdAt +
                ", deletedAt=" + deletedAt +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UnitType unitType = (UnitType) o;
        return Objects.equals(_id, unitType._id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(_id);
    }
}
