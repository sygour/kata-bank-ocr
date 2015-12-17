package com.arolla.bank.ocr;

import com.google.common.collect.Lists;
import org.junit.Assert;
import org.junit.Test;

public class AccountNumberTest {

    public void account_X_should_have_a_checksum_of_Y(int expectedChecksum, AsciNumber... nums) throws Exception {
        final AccountNumber accountNumber = new AccountNumber(Lists.newArrayList(nums));
        Assert.assertEquals(expectedChecksum, accountNumber.checksum());
    }

    @Test
    public void account_0_should_have_a_checksum_of_0() throws Exception {
        account_X_should_have_a_checksum_of_Y(0,
                AsciNumber.ZERO, AsciNumber.ZERO, AsciNumber.ZERO,
                AsciNumber.ZERO, AsciNumber.ZERO, AsciNumber.ZERO,
                AsciNumber.ZERO, AsciNumber.ZERO, AsciNumber.ZERO);
    }

    @Test
    public void account_1_should_have_a_checksum_different_of_1() throws Exception {
        account_X_should_have_a_checksum_of_Y(1,
                AsciNumber.ZERO, AsciNumber.ZERO, AsciNumber.ZERO,
                AsciNumber.ZERO, AsciNumber.ZERO, AsciNumber.ZERO,
                AsciNumber.ZERO, AsciNumber.ZERO, AsciNumber.ONE);
    }

    @Test
    public void account_10_should_have_a_checksum_different_of_2() throws Exception {
        account_X_should_have_a_checksum_of_Y(2,
                AsciNumber.ZERO, AsciNumber.ZERO, AsciNumber.ZERO,
                AsciNumber.ZERO, AsciNumber.ZERO, AsciNumber.ZERO,
                AsciNumber.ZERO, AsciNumber.ONE, AsciNumber.ZERO);
    }

    @Test
    public void account_100_should_have_a_checksum_different_of_3() throws Exception {
        account_X_should_have_a_checksum_of_Y(3,
                AsciNumber.ZERO, AsciNumber.ZERO, AsciNumber.ZERO,
                AsciNumber.ZERO, AsciNumber.ZERO, AsciNumber.ZERO,
                AsciNumber.ONE, AsciNumber.ZERO, AsciNumber.ZERO);
    }

    @Test
    public void account_1000_should_have_a_checksum_different_of_4() throws Exception {
        account_X_should_have_a_checksum_of_Y(4,
                AsciNumber.ZERO, AsciNumber.ZERO, AsciNumber.ZERO,
                AsciNumber.ZERO, AsciNumber.ZERO, AsciNumber.ONE,
                AsciNumber.ZERO, AsciNumber.ZERO, AsciNumber.ZERO);
    }

    @Test
    public void account_10000_should_have_a_checksum_different_of_5() throws Exception {
        account_X_should_have_a_checksum_of_Y(5,
                AsciNumber.ZERO, AsciNumber.ZERO, AsciNumber.ZERO,
                AsciNumber.ZERO, AsciNumber.ONE, AsciNumber.ZERO,
                AsciNumber.ZERO, AsciNumber.ZERO, AsciNumber.ZERO);
    }

    @Test
    public void account_100000_should_have_a_checksum_different_of_6() throws Exception {
        account_X_should_have_a_checksum_of_Y(6,
                AsciNumber.ZERO, AsciNumber.ZERO, AsciNumber.ZERO,
                AsciNumber.ONE, AsciNumber.ZERO, AsciNumber.ZERO,
                AsciNumber.ZERO, AsciNumber.ZERO, AsciNumber.ZERO);
    }

    @Test
    public void account_1000000_should_have_a_checksum_different_of_7() throws Exception {
        account_X_should_have_a_checksum_of_Y(7,
                AsciNumber.ZERO, AsciNumber.ZERO, AsciNumber.ONE,
                AsciNumber.ZERO, AsciNumber.ZERO, AsciNumber.ZERO,
                AsciNumber.ZERO, AsciNumber.ZERO, AsciNumber.ZERO);
    }

    @Test
    public void account_10000000_should_have_a_checksum_different_of_8() throws Exception {
        account_X_should_have_a_checksum_of_Y(8,
                AsciNumber.ZERO, AsciNumber.ONE, AsciNumber.ZERO,
                AsciNumber.ZERO, AsciNumber.ZERO, AsciNumber.ZERO,
                AsciNumber.ZERO, AsciNumber.ZERO, AsciNumber.ZERO);
    }

    @Test
    public void account_100000000_should_have_a_checksum_different_of_9() throws Exception {
        account_X_should_have_a_checksum_of_Y(9,
                AsciNumber.ONE, AsciNumber.ZERO, AsciNumber.ZERO,
                AsciNumber.ZERO, AsciNumber.ZERO, AsciNumber.ZERO,
                AsciNumber.ZERO, AsciNumber.ZERO, AsciNumber.ZERO);
    }

    @Test(expected = NumberFormatException.class)
    public void illegal_account_should_throw_an_exception_on_checksum() throws Exception {
        new AccountNumber(Lists.newArrayList(AsciNumber.ONE, AsciNumber.THREE, AsciNumber.FIVE,
                AsciNumber.ONE, AsciNumber.NINE, AsciNumber.ILLEGAL,
                AsciNumber.ONE, AsciNumber.ILLEGAL, AsciNumber.ONE)).checksum();
    }
}