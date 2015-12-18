package com.arolla.bank.ocr;

import java.util.Set;

public interface Correctable extends Checksumable {

    Set<? extends Correctable> getCorrections();

}
