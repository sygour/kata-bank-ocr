package com.arolla.bank.ocr;

public enum Status {
    OK(""),
    ERROR("ERR"),
    ILLEGAL("ILL"),
    ;

    public final String message;

    Status(String message) {
        this.message = message;
    }

    public static Status fromChecksumable(Checksumable checksumable) {
        try {
            final int checksum = checksumable.checksum();
            return checksum == 0 ? OK : ERROR;
        } catch (NumberFormatException nfe) {
            return ILLEGAL;
        }
    }
}
