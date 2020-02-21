package com.mr2.zaiko.zaiko2.domain.inhouse.equipment;

import androidx.annotation.NonNull;

import com.mr2.zaiko.zaiko2.domain.common.ValueObject;
import com.mr2.zaiko.zaiko2.domain.inhouse.user.UserId;

import java.util.Objects;

public final class AutomaticRequest extends ValueObject {
    public enum JudgmentCriteria { Regularly, Quantitative }
    @NonNull private final JudgmentCriteria judgmentCriteria;
    private final int orderPoint;
    private final int orderQuantity;
    @NonNull private final UserId registeredUser;

    public AutomaticRequest(@NonNull JudgmentCriteria judgmentCriteria, int orderPoint, int orderQuantity, @NonNull UserId registeredUser) {
        this.judgmentCriteria = judgmentCriteria;
        this.orderPoint = orderPoint;
        this.orderQuantity = orderQuantity;
        this.registeredUser = registeredUser;
    }

    @NonNull
    public JudgmentCriteria judgmentCriteria() {
        return judgmentCriteria;
    }

    public int orderPoint() {
        return orderPoint;
    }

    public int orderQuantity() {
        return orderQuantity;
    }

    @NonNull
    public UserId registeredUser() {
        return registeredUser;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AutomaticRequest)) return false;
        AutomaticRequest that = (AutomaticRequest) o;
        return orderPoint == that.orderPoint &&
                orderQuantity == that.orderQuantity &&
                judgmentCriteria == that.judgmentCriteria &&
                registeredUser.equals(that.registeredUser);
    }

    @Override
    public int hashCode() {
        return Objects.hash(judgmentCriteria, orderPoint, orderQuantity, registeredUser);
    }
}
