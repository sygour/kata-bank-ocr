package com.arolla.bank.ocr;

public enum AsciNumber {
    ZERO    ('0', " _ ", "| |", "|_|"),
    ONE     ('1', "   ", "  |", "  |"),
    TWO     ('2', " _ ", " _|", "|_ "),
    THREE   ('3', " _ ", " _|", " _|"),
    FOUR    ('4', "   ", "|_|", "  |"),
    FIVE    ('5', " _ ", "|_ ", " _|"),
    SIX     ('6', " _ ", "|_ ", "|_|"),
    SEVEN   ('7', " _ ", "  |", "  |"),
    HEIGHT  ('8', " _ ", "|_|", "|_|"),
    NINE    ('9', " _ ", "|_|", " _|"),
    ILLEGAL ('?', "___", "___", "___"),
    ;

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
        // throw new IllegalArgumentException(String.format("Unknown character: \n%s\n%s\n%s", l1, l2, l3));
    }

    public String number() {
        return String.valueOf(number);
    }
}
