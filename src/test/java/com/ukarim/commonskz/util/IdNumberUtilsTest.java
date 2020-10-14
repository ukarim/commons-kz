package com.ukarim.commonskz.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class IdNumberUtilsTest {

    @ParameterizedTest
    @ValueSource(strings = {
            "", // empty
            "    ", // blank
            "wqyoi2y322", // alphanumeric
            "12ljdd",
            "3423523522121" // length != 12
    })
    public void checkInvalidIins(String invalidIin) {
        Assertions.assertFalse(IdNumberUtils.isValidIin(invalidIin));
    }

    @Test
    public void checkNullIin() {
        Assertions.assertFalse(IdNumberUtils.isValidIin(null));
    }

    @Test
    public void checkNullBin() {
        Assertions.assertFalse(IdNumberUtils.isValidBin(null));
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "", // empty
            "    ", // blank
            "wqyoi2y322", // alphanumeric
            "12ljdd",
            "3423523522121" // length != 12
    })
    public void checkInvalidBins(String invalidBin) {
        Assertions.assertFalse(IdNumberUtils.isValidBin(invalidBin));
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "940140000385", // halyk bank
            "971240001315", // kaspi bank
            "970440003326", // kolesa group
            "000140002217", // kazpost
    })
    public void checkValidBins(String validBin) {
        Assertions.assertTrue(IdNumberUtils.isValidBin(validBin));
    }
}
