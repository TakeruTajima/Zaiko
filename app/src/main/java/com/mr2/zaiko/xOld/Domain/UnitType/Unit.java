package com.mr2.zaiko.xOld.Domain.UnitType;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.mr2.zaiko.xOld.Domain.CreatedDateTime;
import com.mr2.zaiko.xOld.Domain.DeletedDateTime;
import com.mr2.zaiko.xOld.Domain.Id;

import java.util.Objects;

@Deprecated
public class Unit {
    private final Id _id;//変更を受け入れるか？
    private final UnitTypeName name;
    private final CreatedDateTime createdAt;
    private final DeletedDateTime deletedAt;

    private Unit(@NonNull Id _id){
        this._id = _id;
        name = null;
        createdAt = null;
        deletedAt = null;
    }

    private Unit(@NonNull UnitTypeName name) {
        this._id = null;
        this.name = name;
        this.createdAt = null;
        this.deletedAt = null;
    }

    Unit(@NonNull Id _id, @NonNull UnitTypeName name, @NonNull CreatedDateTime createdAt, @Nullable DeletedDateTime deletedAt) {
        this._id = _id;
        this.name = name;
        this.createdAt = createdAt;
        this.deletedAt = deletedAt;
    }

    public static Unit of(@NonNull UnitTypeName name){
        return new Unit(name);
    }

    public static Unit of(@NonNull Id _id){
        return new Unit(_id);
    }

    public Id get_id() {return _id; }

    public UnitTypeName getName() {
        return name;
    }

    public CreatedDateTime getCreatedAt() {return createdAt;}

    public DeletedDateTime getDeletedAt() {return deletedAt;}

    @Override
    public String toString() {
        return "Unit{" +
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
        Unit unit = (Unit) o;
        return Objects.equals(_id, unit._id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(_id);
    }
}
