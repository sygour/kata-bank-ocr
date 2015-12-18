package com.arolla.bank.ocr;

import java.util.ArrayList;
import java.util.List;

public class AccountNumber implements Checksumable {

    private final List<MatchingSet<AsciNumber>> digits;
    private final Status status;

    public AccountNumber(List<MatchingSet<AsciNumber>> nums) {
        digits = nums;
        status = Status.fromChecksumable(this);
    }

    public AccountNumber(String nums) {
        this(getMatchingSets(nums));
    }

    private static List<MatchingSet<AsciNumber>> getMatchingSets(String nums) {
        final List<MatchingSet<AsciNumber>> digitList = new ArrayList<>();
        for (char c : nums.toCharArray()) {
            final MatchingSet<AsciNumber> matchingSet = new MatchingSet<>();
            matchingSet.setMatch(AsciNumber.valueOf(c));
            digitList.add(matchingSet);
        }
        return digitList;
    }

    public String getNumber() {
        final StringBuilder stringBuilder = new StringBuilder();
        for (MatchingSet<AsciNumber> digit : digits) {
            stringBuilder.append(digit.getMatch().number());
        }
        return stringBuilder.toString();
    }

    @Override
    public int checksum() {
        return sum(getNumber(), 0) % 11;
    }

    private static int sum(String string, int iterCount) {
        if (string == null || string.length() <= 0) {
            return 0;
        } else {
            final int splitIndex = string.length() - 1;
            final int nextCount = iterCount + 1;
            final String substring = string.substring(splitIndex);
            final int increment = nextCount * Integer.valueOf(substring);
            return increment + sum(string.substring(0, splitIndex), nextCount);
        }
    }

    public Status getStatus() {
        return status;
    }

    @Override
    public String toString() {
        final StringBuilder stringBuilder = new StringBuilder(getNumber());
        if (status != Status.OK) {
            stringBuilder.append(" ").append(status.message);
        }
        return stringBuilder.toString();
    }
}
