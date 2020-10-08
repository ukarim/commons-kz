package com.ukarim.commonskz.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
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

    @Test
    public void multipleCheckLength() {
        int len = 21;
        for (int i = 0; i < 1000; i++) {
            String randomString = RandomString.generate(len);
            Assertions.assertEquals(len, randomString.length());
        }
    }
}
