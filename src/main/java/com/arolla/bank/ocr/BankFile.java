package com.arolla.bank.ocr;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class BankFile implements AccountListing {
    private static final int CHAR_LENGTH = 3;
    private static final int CHAR_HEIGHT = 3;
    private static final int LINE_NUMBER_COUNT = 9;
    private final List<AccountNumber> accountNumbers = new ArrayList<>();

    public BankFile(String s) throws IOException {
        final InputStream inputStream = getClass().getResourceAsStream(s);
        final BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

        final List<String> nextNumLine = readAccountLines(reader);

        if (nextNumLine.size() >= CHAR_LENGTH) {
            final List<String> linesToProcess = nextNumLine.subList(0, CHAR_LENGTH);
            final List<MatchingSet<AsciNumber>> numbers = findNumbers(linesToProcess.get(0), linesToProcess.get(1), linesToProcess.get(2));
            getAccountNumbers().add(new AccountNumber(numbers));
        }
    }

    private List<MatchingSet<AsciNumber>> findNumbers(String first, String second, String third) {
        final List<MatchingSet<AsciNumber>> asciNumbers = new ArrayList<>();
        for (int i = 0; i < LINE_NUMBER_COUNT; i++) {
            final int begin = i * CHAR_LENGTH;
            final String firstElem = first.substring(begin, begin + CHAR_LENGTH);
            final String secondElem = second.substring(begin, begin + CHAR_LENGTH);
            final String thirdElem = third.substring(begin, begin + CHAR_LENGTH);
            final MatchingSet<AsciNumber> matchings = AsciNumber.matchings(firstElem, secondElem, thirdElem);
            asciNumbers.add(matchings);
        }
        return asciNumbers;
    }

    private List<String> readAccountLines(BufferedReader reader) throws IOException {
        final List<String> nextNumLine = new ArrayList<>();

        while (reader.ready() && nextNumLine.size() < CHAR_HEIGHT + 1) {
            nextNumLine.add(reader.readLine());
        }
        return nextNumLine;
    }

    public List<AccountNumber> getAccountNumbers() {
        return accountNumbers;
    }
}
