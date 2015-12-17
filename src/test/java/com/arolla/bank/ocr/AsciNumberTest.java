package com.arolla.bank.ocr;

import com.google.common.collect.Sets;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

public class AsciNumberTest {

    private void setShouldContainsOnly(Set<AsciNumber> set, Set<AsciNumber> contained) {
        final HashSet<AsciNumber> notContained = Sets.newHashSet(AsciNumber.values());
        notContained.removeAll(contained);
        Assert.assertTrue(set.containsAll(contained));
        Assert.assertFalse(set.containsAll(notContained));
    }

    @Test
    public void zero_should_match_zero_and_a_possible_height() throws Exception {
        final MatchingSet<AsciNumber> matchingSet = AsciNumber.matchings(
                " _ ",
                "| |",
                "|_|");
        Assert.assertEquals(AsciNumber.ZERO, matchingSet.getMatch());
        setShouldContainsOnly(matchingSet.getPossibles(), Sets.newHashSet(AsciNumber.HEIGHT));
    }

    @Test
    public void height_should_match_height_and_possible_zero_six_nine() throws Exception {
        final MatchingSet<AsciNumber> matchingSet = AsciNumber.matchings(
                " _ ",
                "|_|",
                "|_|");
        Assert.assertEquals(AsciNumber.HEIGHT, matchingSet.getMatch());
        setShouldContainsOnly(matchingSet.getPossibles(), Sets.newHashSet(AsciNumber.ZERO, AsciNumber.SIX, AsciNumber.NINE));
    }
}