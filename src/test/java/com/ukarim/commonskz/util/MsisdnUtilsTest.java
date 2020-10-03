package com.ukarim.commonskz.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Optional;

class MsisdnUtilsTest {

    @ParameterizedTest
    @ValueSource(strings = {
            "77012111966",
            "77059873415",
            "77023456768",
            "77771234567",
            "77479375377",
            "77757568391",
            "77089386497"
    })
    public void checkValidMsisdns(String validMsisdn) {
        Assertions.assertTrue(MsisdnUtils.isValidMsisdn(validMsisdn));
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "7012111966",
            "770598734151",
            "770234www",
            "",
            "   ",
            "+77089386497"
    })
    public void checkInvalidMsisdns(String invalidMsisdn) {
        Assertions.assertFalse(MsisdnUtils.isValidMsisdn(invalidMsisdn));
    }

    @Test
    public void checkNullMsisdn() {
        Assertions.assertFalse(MsisdnUtils.isValidMsisdn(null));
    }

    @ParameterizedTest
    @CsvSource(value = {
            "+77012111966,77012111966",
            "87012111966,77012111966",
            "8(701)2111966,77012111966",
            "7 (701) 211-19-66,77012111966",
            "+7 701 211 19 66,77012111966",
            "8 701 21-11-966,77012111966",
            "+7 (701) 211-19-66,77012111966"
    })
    public void checkSuccessfulConversion(String input, String expected) {
        Optional<String> maybe = MsisdnUtils.toMsisdn(input);
        Assertions.assertTrue(maybe.isPresent());
        Assertions.assertEquals(expected, maybe.get());
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "+77012111966c",
            " ",
            "   ",
            "87012111966a",
            "iyerwdfbnvc"
    })
    public void checkFailedConversion(String input) {
        Optional<String> maybe = MsisdnUtils.toMsisdn(input);
        Assertions.assertFalse(maybe.isPresent());
    }
}
