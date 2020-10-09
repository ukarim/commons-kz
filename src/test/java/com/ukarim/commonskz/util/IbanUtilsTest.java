package com.ukarim.commonskz.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

class IbanUtilsTest {

    @ParameterizedTest
    @CsvSource({
            "BE71096123456769,BE71 0961 2345 6769",
            "CH5604835012345678009,CH56 0483 5012 3456 7800 9",
            "GB98MIDL07009312345678,GB98 MIDL 0700 9312 3456 78",
            "GR9608100010000001234567890,GR96 0810 0010 0000 0123 4567 890",
            "PK00BANK0000123456789000,PK00 BANK 0000 1234 5678 9000",
    })
    public void checkFormat(String input, String expected) {
        Assertions.assertEquals(expected, IbanUtils.printableFormat(input));
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "BE71096123456769", "CH5604835012345678009", "GB98MIDL07009312345678",
            "GR9608100010000001234567890", "SM76P0854009812123456789123", "SC52BAHL01031234567890123456USD",
            "LC14BOSL123456789012345678901234", "TN5904018104004942712345", "KZ563190000012344567"
    })
    public void checkValidIbans(String iban) {
        Assertions.assertTrue(IbanUtils.isValidIban(iban));
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "   ",
            "",
            "TN5904018104004942712341",
            "BE71096123456760"
    })
    public void checkInvalidIbans(String invalidIban) {
        Assertions.assertFalse(IbanUtils.isValidIban(invalidIban));
    }

    @Test
    public void checkNulIbans() {
        Assertions.assertFalse(IbanUtils.isValidIban(null));
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "KZ563190000012344567",
            "KZ75125KZT1001300335"
    })
    public void checkValidKzIbans(String kzIban) {
        Assertions.assertTrue(IbanUtils.isValidKzIban(kzIban));
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "KZ5631900000123445671", // invalid length
            "AZ75125KZT1001300335", // invalid country code
            "KZ75125KZT1001300336", // ibvalid checksum
            "    ",
            "",
            "kz75125kzt1001300335", // valid IBAN in lowercase
    })
    public void checkInvalidKzIbans(String invalidIban) {
        Assertions.assertFalse(IbanUtils.isValidKzIban(invalidIban));
    }

    @Test
    public void checkNullKzIban() {
        Assertions.assertFalse(IbanUtils.isValidKzIban(null));
    }
}
