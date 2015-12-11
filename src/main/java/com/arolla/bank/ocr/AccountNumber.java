package com.arolla.bank.ocr;

import java.util.List;

public class AccountNumber {

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

}
