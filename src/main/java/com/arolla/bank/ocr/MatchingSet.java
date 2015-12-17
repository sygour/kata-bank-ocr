package com.arolla.bank.ocr;

import com.google.common.base.MoreObjects;
import com.google.common.collect.Sets;

import java.util.Set;

public class MatchingSet<T> {
    private T match;
    private final Set<T> possibles = Sets.newHashSet();

    public T getMatch() {
        return match;
    }

    public void setMatch(T match) {
        this.match = match;
    }

    public Set<T> getPossibles() {
        return possibles;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("match", match)
                .add("possibles", possibles.toArray())
                .toString();
    }
}
