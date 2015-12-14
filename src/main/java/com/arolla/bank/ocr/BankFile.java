package com.arolla.bank.ocr;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class BankFile implements AccountListing {
    private final List<AccountNumber> accountNumbers = new ArrayList<AccountNumber>();

    public BankFile(String s) throws IOException {
        final InputStream inputStream = getClass().getResourceAsStream(s);
        final BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

        final List<String> nextNumLine = readAccountLines(reader);

        if (nextNumLine.size() >= 3) {
            final List<String> linesToProcess = nextNumLine.subList(0, 3);
            final List<AsciNumber> numbers = findNumbers(linesToProcess.get(0), linesToProcess.get(1), linesToProcess.get(2));
            getAccountNumbers().add(new AccountNumber(numbers));
        }
    }

    private List<AsciNumber> findNumbers(String first, String second, String third) {
        final List<AsciNumber> asciNumbers = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            final int begin = i * 3;
            final String firstElem = first.substring(begin, begin + 3);
            final String secondElem = second.substring(begin, begin + 3);
            final String thirdElem = third.substring(begin, begin + 3);
            asciNumbers.add(AsciNumber.matching(firstElem, secondElem, thirdElem));
        }
        return asciNumbers;
    }

    private List<String> readAccountLines(BufferedReader reader) throws IOException {
        final List<String> nextNumLine = new ArrayList<String>();

        while (reader.ready() && nextNumLine.size() < 4) {
            nextNumLine.add(reader.readLine());
        }
        return nextNumLine;
    }

    public List<AccountNumber> getAccountNumbers() {
        return accountNumbers;
    }
}
