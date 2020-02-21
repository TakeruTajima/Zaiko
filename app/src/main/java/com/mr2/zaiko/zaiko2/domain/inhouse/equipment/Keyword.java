package com.mr2.zaiko.zaiko2.domain.inhouse.equipment;

import androidx.annotation.NonNull;

import com.mr2.zaiko.zaiko2.domain.common.ValueObject;

import java.util.Objects;

public final class Keyword extends ValueObject {
    @NonNull private final String word;

    public Keyword(@NonNull String word) {
        this.word = word;
    }

    public String word(){ return word; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Keyword)) return false;
        Keyword keyword = (Keyword) o;
        return word.equals(keyword.word);
    }

    @Override
    public int hashCode() {
        return Objects.hash(word);
    }
}
