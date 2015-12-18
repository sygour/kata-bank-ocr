package com.arolla.bank.ocr;

import com.google.common.collect.Sets;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class AccountNumber implements Correctable {

    private final List<MatchingSet<AsciNumber>> matchingSets;
    private final Status status;

    public AccountNumber(List<MatchingSet<AsciNumber>> nums) {
        matchingSets = nums;
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
        for (MatchingSet<AsciNumber> digit : matchingSets) {
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

    @Override
    public Set<AccountNumber> getCorrections() {
        final Set<AccountNumber> accountNumbers = Sets.newHashSet();
        if (getStatus() == Status.OK) {
            accountNumbers.add(this);
        } else {
            final Set<AccountNumber> possibleCorrections = getPossibleCorrections();
            for (AccountNumber possibleCorrection : possibleCorrections) {
                if (possibleCorrection.getStatus() == Status.OK) {
                    accountNumbers.add(possibleCorrection);
                }
            }
        }
        return accountNumbers;
    }

    private Set<AccountNumber> getPossibleCorrections() {
        final Set<AccountNumber> result = Sets.newHashSet();
        for (MatchingSet<AsciNumber> match : matchingSets) {
            if (match.getPossibles().isEmpty()) {

            } else {

            }
        }

        return result;
    }
}
