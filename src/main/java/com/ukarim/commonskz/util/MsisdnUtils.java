package com.ukarim.commonskz.util;

import java.util.Optional;

public final class MsisdnUtils {

    private static final String VALID_MSISDN_REGEX = "^77[0-9]{9}$";

    private MsisdnUtils() {}


    /**
     * Check if string is valid MSISDN or not
     * @param msisdn MSISDN string
     * @return Is input a valid MSISDN or not
     */
    public static boolean isValidMsisdn(String msisdn) {
        if (msisdn == null) {
            return false;
        }
        return msisdn.matches(VALID_MSISDN_REGEX);
    }

    /**
     * Will try to convert input string to valid MSISDN eliminating the following characters:
     * <ul>
     * <li>'-'</li>
     * <li>'('</li>
     * <li>')'</li>
     * <li>leading '+'</li>
     * <li>space characters</li>
     * </ul>
     * @param input MSISDN
     * @return Result of the conversion to MSISDN
     */
    public static Optional<String> toMsisdn(String input) {
        String normalized = normalizeInput(input);

        if (isValidMsisdn(normalized)) {
            return Optional.of(normalized);
        }

        int inputLength = normalized.length();
        char[] inputChars = normalized.toCharArray();

        if (!onlyNumbers(inputChars)) {
            return Optional.empty();
        }

        if (inputLength == 12 && normalized.startsWith("+77")) {
            return Optional.of(normalized.substring(1));
        }

        if (inputLength == 11 && normalized.startsWith("87")) {
            return Optional.of("7" + normalized.substring(1));
        }

        return Optional.empty();
    }

    private static boolean onlyNumbers(char[] charArr) {
        boolean returnVal = false;
        for (char c : charArr) {
            returnVal = c > 47 && c < 58;
        }
        return returnVal;
    }

    private static String normalizeInput(String input) {
        char[] inputChars = input.toCharArray();
        StringBuilder builder = new StringBuilder();
        for (char ch : inputChars) {
            switch (ch) {
                case ' ':
                case '-':
                case '(':
                case ')':
                    break;
                default:
                    builder.append(ch);
            }
        }
        return builder.toString();
    }
}
