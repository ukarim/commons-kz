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
        Assertions.assertFalse(IdNumberUtils.isIIN(invalidIin));
    }

    @Test
    public void checkNullIin() {
        Assertions.assertFalse(IdNumberUtils.isIIN(null));
    }

    @Test
    public void checkNullBin() {
        Assertions.assertFalse(IdNumberUtils.isBIN(null));
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
        Assertions.assertFalse(IdNumberUtils.isBIN(invalidBin));
    }
}
