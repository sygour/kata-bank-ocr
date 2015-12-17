package com.arolla.bank.ocr;

import org.junit.Assert;
import org.junit.Test;

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
}
