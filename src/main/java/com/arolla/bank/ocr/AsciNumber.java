package com.arolla.bank.ocr;

import com.google.common.collect.Sets;

import java.util.Set;

public enum AsciNumber {
    ZERO('0',
            " _ ",
            "| |",
            "|_|"),
    ONE('1',
            "   ",
            "  |",
            "  |"),
    TWO('2',
            " _ ",
            " _|",
            "|_ "),
    THREE('3',
            " _ ",
            " _|",
            " _|"),
    FOUR('4',
            "   ",
            "|_|",
            "  |"),
    FIVE('5',
            " _ ",
            "|_ ",
            " _|"),
    SIX('6',
            " _ ",
            "|_ ",
            "|_|"),
    SEVEN('7',
            " _ ",
            "  |",
            "  |"),
    HEIGHT('8',
            " _ ",
            "|_|",
            "|_|"),
    NINE('9',
            " _ ",
            "|_|",
            " _|"),
    ILLEGAL('?', "___", "___", "___"),;

    private final char number;
    private final String first;
    private final String second;
    private final String third;

    AsciNumber(char number, String first, String second, String third) {
        this.number = number;
        this.first = first;
        this.second = second;
        this.third = third;
    }

    public static AsciNumber matching(String l1, String l2, String l3) {
        for (AsciNumber asciNumber : AsciNumber.values()) {
            if (asciNumber.first.equals(l1)
                    && asciNumber.second.equals(l2)
                    && asciNumber.third.equals(l3)) {
                return asciNumber;
            }
        }
        return ILLEGAL;
    }

    public static MatchingSet<AsciNumber> matchings(String l1, String l2, String l3) {
        final MatchingSet<AsciNumber> result = new MatchingSet<>();
        result.setMatch(matching(l1, l2, l3));
        result.getPossibles().addAll(possibleMatches(l1, l2, l3));
        return result;
    }

    private static Set<AsciNumber> possibleMatches(String l1, String l2, String l3) {
        final Set<AsciNumber> result = Sets.newHashSet();
        for (AsciNumber asciNumber : AsciNumber.values()) {
            if (errorCount(l1, l2, l3, asciNumber) == 1) {
                result.add(asciNumber);
            }
        }
        return result;
    }

    private static int errorCount(String line1, String line2, String line3, AsciNumber number) {
        return errorCount(line1, number.first) + errorCount(line2, number.second) + errorCount(line3, number.third);
    }

    private static int errorCount(String line, String reference) {
        int result = 0;
        final int limit = Math.min(line.length(), reference.length());
        for (int i = 0; i < limit; i++) {
            if (line.charAt(i) != reference.charAt(i)) {
                result++;
            }
        }
        return result;
    }

    public String number() {
        return String.valueOf(number);
    }

    public static AsciNumber valueOf(char c) {
        for (AsciNumber asciNumber : values()) {
            if (asciNumber.number == c) {
                return asciNumber;
            }
        }
        return ILLEGAL;
    }
}
