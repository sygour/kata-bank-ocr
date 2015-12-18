package com.arolla.bank.ocr;

import com.google.common.collect.Sets;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import java.util.Set;

public class ChecksumTest {

    @Test
    public void checksum_ok_457508000() throws Exception {
        Assert.assertEquals("457508000", new AccountNumber("457508000").toString());
    }

    @Test
    public void checksum_err_664371495() throws Exception {
        Assert.assertEquals("664371495 ERR", new AccountNumber("664371495").toString());
    }

    @Test
    public void checksum_ill_86110XX36() throws Exception {
        Assert.assertEquals("86110??36 ILL", new AccountNumber("86110??36").toString());
    }

    @Test
    public void account_457508000_should_return_itself_as_a_correction() throws Exception {
        final AccountNumber accountNumber = new AccountNumber("457508000");
        final Set<AccountNumber> corrections = accountNumber.getCorrections();
        Assert.assertEquals(1, corrections.size());
        Assert.assertTrue(corrections.contains(accountNumber));
    }

    /*
         _  _  _  _  _  _  _  _  _
        |_||_||_||_||_||_||_||_||_|
        |_||_||_||_||_||_||_||_||_|

        => 888888888 AMB ['888886888', '888888880', '888888988']
     */

    @Test
    @Ignore("not ready")
    public void account_888888888_should_return_many_possible_correction() {
        final AccountNumber accountNumber = new AccountNumber("888888888");
        Assert.assertEquals("888888888 ERR", accountNumber.toString());
        final Set<AccountNumber> corrections = accountNumber.getCorrections();
        Assert.assertEquals(3, corrections.size());
        Assert.assertTrue(corrections.containsAll(Sets.newHashSet(
                new AccountNumber("888886888"),
                new AccountNumber("888888880"),
                new AccountNumber("888888988"))));
    }

}
