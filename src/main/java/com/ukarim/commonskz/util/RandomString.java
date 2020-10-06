package com.ukarim.commonskz.util;

import java.security.SecureRandom;

/**
 * Random string generator based on <i>java.security.SecureRandom</i>
 * that can be used for request, trace ids and etc. <br>
 * Default alphabet: 0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ <br>
 * Default length: 16 <br>
 */
public final class RandomString {

    private static final SecureRandom SECURE_RANDOM = new SecureRandom();

    private static final char[] DEFAULT_ALPHABET =
            "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();

    private static final int DEFAULT_LEN = 16;

    private RandomString() {}

    /**
     * Generate random string of length <i>len</i> using provided <i>alphabet</i>
     * @param alphabet alphabet characters
     * @param len length of random string
     * @return random string
     */
    public static String generate(char[] alphabet, int len) {
        byte[] randomBytes = new byte[len];
        int alphabetMaxIdx = alphabet.length - 1;
        SECURE_RANDOM.nextBytes(randomBytes);
        StringBuilder strBuilder = new StringBuilder();
        for (byte randomByte : randomBytes) {
            int idx = Math.abs(randomByte) % alphabetMaxIdx; // do not overflow
            strBuilder.append(alphabet[idx]); // append randomly selected char
        }
        return strBuilder.toString();
    }

    /**
     * Generate random string of length <i>len</i> using default alphabet
     * @param len length of random string
     * @return random string
     */
    public static String generate(int len) {
        return generate(DEFAULT_ALPHABET, len);
    }

    /**
     * Generate random string using default alphabet and length
     * @return random string
     */
    public static String generate() {
        return generate(DEFAULT_ALPHABET, DEFAULT_LEN);
    }
}
