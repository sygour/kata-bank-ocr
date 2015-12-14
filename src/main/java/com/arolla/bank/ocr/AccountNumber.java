package com.arolla.bank.ocr;

import java.util.List;

public class AccountNumber implements Checksumable {

    private final String number;

    public AccountNumber(List<AsciNumber> nums) {
        final StringBuilder stringBuilder = new StringBuilder();
        for (AsciNumber num : nums) {
            stringBuilder.append(num.number());
        }
        number = stringBuilder.toString();
    }

    public String getNumber() {
        return number;
    }

    @Override
    public int checksum() {
        return sum(number, 0) % 11;
    }

    private static int sum(String string, int iterCount){
        if (string == null || string.length() <= 0) {
            return 0;
        } else {
            final int splitIndex = string.length() - 1;
            final int nextCount = iterCount + 1;
            final int increment = nextCount * Integer.valueOf(string.substring(splitIndex));
            return increment + sum(string.substring(0, splitIndex), nextCount);
        }
    }
}
