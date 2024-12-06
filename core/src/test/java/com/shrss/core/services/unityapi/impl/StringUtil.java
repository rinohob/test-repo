package com.shrss.core.services.unityapi.impl;
public class StringUtil {

    /**
     * Checks if none of the CharSequence objects in the given array are null, empty, or whitespace only.
     * 
     * @param values the CharSequence array to check
     * @return true if none are null, empty, or whitespace only; false otherwise
     */
    public static boolean isNoneBlank(CharSequence... values) {
        if (values == null) {
            return false;
        }
        for (CharSequence value : values) {
            if (value == null || value.toString().trim().isEmpty()) {
                return false;
            }
        }
        return true;
    }
}
