package com.arolla.bank.ocr;

import com.google.common.base.Strings;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class BankFileAccountListingTest {

    @Test
    public void empty_file_should_return_no_account_number() throws Exception {
        final AccountListing accountListing = new BankFile("/empty.txt");
        final List<AccountNumber> accountNumbers = accountListing.getAccountNumbers();
        Assert.assertTrue(accountNumbers.isEmpty());
    }

    private void single_number_should_be_recognised(AsciNumber number, String singleEntryFileName) throws Exception {
        account_number_should_be_recognised(Strings.repeat(number.number(), 9), String.format("single/%s", singleEntryFileName));
    }

    private void account_number_should_be_recognised(String accountNumber, String entryFileName) throws Exception {
        final AccountListing accountListing = new BankFile(String.format("/%s", entryFileName));
        final List<AccountNumber> accountNumbers = accountListing.getAccountNumbers();
        Assert.assertEquals(1, accountNumbers.size());
        Assert.assertEquals(accountNumber, accountNumbers.get(0).getNumber());
    }

    @Test
    public void file_with_number_0_should_return_1_account_with_number_0() throws Exception {
        single_number_should_be_recognised(AsciNumber.ZERO, "0.txt");
    }

    @Test
    public void file_with_number_1_should_return_1_account_with_number_1() throws Exception {
        single_number_should_be_recognised(AsciNumber.ONE, "1.txt");
    }

    @Test
    public void file_with_number_2_should_return_1_account_with_number_2() throws Exception {
        single_number_should_be_recognised(AsciNumber.TWO, "2.txt");
    }

    @Test
    public void file_with_number_3_should_return_1_account_with_number_3() throws Exception {
        single_number_should_be_recognised(AsciNumber.THREE, "3.txt");
    }

    @Test
    public void file_with_number_4_should_return_1_account_with_number_4() throws Exception {
        single_number_should_be_recognised(AsciNumber.FOUR, "4.txt");
    }

    @Test
    public void file_with_number_5_should_return_1_account_with_number_5() throws Exception {
        single_number_should_be_recognised(AsciNumber.FIVE, "5.txt");
    }

    @Test
    public void file_with_number_6_should_return_1_account_with_number_6() throws Exception {
        single_number_should_be_recognised(AsciNumber.SIX, "6.txt");
    }

    @Test
    public void file_with_number_7_should_return_1_account_with_number_7() throws Exception {
        single_number_should_be_recognised(AsciNumber.SEVEN, "7.txt");
    }

    @Test
    public void file_with_number_8_should_return_1_account_with_number_8() throws Exception {
        single_number_should_be_recognised(AsciNumber.HEIGHT, "8.txt");
    }

    @Test
    public void file_with_number_9_should_return_1_account_with_number_9() throws Exception {
        single_number_should_be_recognised(AsciNumber.NINE, "9.txt");
    }

    @Test
    public void file_with_all_numbers_should_return_123456789() throws Exception {
        account_number_should_be_recognised("123456789", "all-numbers.txt");
    }

}