package com.arolla.bank.ocr;

import com.google.common.collect.Lists;
import org.junit.Assert;
import org.junit.Test;

public class AccountNumberTest {

    public void account_X_should_have_a_checksum_of_Y(int expectedChecksum, String nums) throws Exception {
        final AccountNumber accountNumber = new AccountNumber(nums);
        Assert.assertEquals(expectedChecksum, accountNumber.checksum());
    }

    @Test
    public void account_0_should_have_a_checksum_of_0() throws Exception {
        account_X_should_have_a_checksum_of_Y(0, "000000000");
    }

    @Test
    public void account_1_should_have_a_checksum_different_of_1() throws Exception {
        account_X_should_have_a_checksum_of_Y(1, "000000001");
    }

    @Test
    public void account_10_should_have_a_checksum_different_of_2() throws Exception {
        account_X_should_have_a_checksum_of_Y(2, "000000010");
    }

    @Test
    public void account_100_should_have_a_checksum_different_of_3() throws Exception {
        account_X_should_have_a_checksum_of_Y(3, "000000100");
    }

    @Test
    public void account_1000_should_have_a_checksum_different_of_4() throws Exception {
        account_X_should_have_a_checksum_of_Y(4, "000001000");
    }

    @Test
    public void account_10000_should_have_a_checksum_different_of_5() throws Exception {
        account_X_should_have_a_checksum_of_Y(5, "000010000");
    }

    @Test
    public void account_100000_should_have_a_checksum_different_of_6() throws Exception {
        account_X_should_have_a_checksum_of_Y(6, "000100000");
    }

    @Test
    public void account_1000000_should_have_a_checksum_different_of_7() throws Exception {
        account_X_should_have_a_checksum_of_Y(7, "001000000");
    }

    @Test
    public void account_10000000_should_have_a_checksum_different_of_8() throws Exception {
        account_X_should_have_a_checksum_of_Y(8, "010000000");
    }

    @Test
    public void account_100000000_should_have_a_checksum_different_of_9() throws Exception {
        account_X_should_have_a_checksum_of_Y(9, "100000000");
    }

    @Test(expected = NumberFormatException.class)
    public void illegal_account_should_throw_an_exception_on_checksum() throws Exception {
        new AccountNumber("13519?1?1").checksum();
    }
}