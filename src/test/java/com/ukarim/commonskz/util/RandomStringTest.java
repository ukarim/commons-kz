package com.ukarim.commonskz.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class RandomStringTest {

    @ParameterizedTest
    @ValueSource(ints = {8, 11, 16 ,21})
    public void checkLength(int len) {
        String randomString = RandomString.generate(len);
        System.out.println(randomString);
        Assertions.assertEquals(len, randomString.length());
    }
}
