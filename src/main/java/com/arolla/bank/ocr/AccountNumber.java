package com.arolla.bank.ocr;

import java.util.List;

public class AccountNumber implements Checksumable {

    private final String number;
    private final Status status;

    private static String stringFromAsciNumber(List<AsciNumber> nums) {
        final StringBuilder stringBuilder = new StringBuilder();
        for (final AsciNumber num : nums) {
            stringBuilder.append(num.number());
        }
        return stringBuilder.toString();
    }

    public AccountNumber(List<AsciNumber> nums) {
        this(stringFromAsciNumber(nums));
    }

    public AccountNumber(String nums) {
        number = nums;
        status = Status.fromChecksumable(this);
    }

    public String getNumber() {
        return number;
    }

    @Override
    public int checksum() {
        return sum(number, 0) % 11;
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
        final StringBuilder stringBuilder = new StringBuilder(number);
        if (status != Status.OK) {
            stringBuilder.append(" ").append(status.message);
        }
        return stringBuilder.toString();
    }
}
