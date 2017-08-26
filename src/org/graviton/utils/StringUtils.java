package org.graviton.utils;

public class StringUtils {

    private static String capitalize(String value) {
        return Character.toUpperCase(value.charAt(0)) + value.substring(1);
    }

}
