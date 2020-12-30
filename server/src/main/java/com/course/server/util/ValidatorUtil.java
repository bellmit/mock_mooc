package com.course.server.util;

import com.course.server.exception.ValidatorException;
import org.springframework.util.StringUtils;

public class ValidatorUtil {

    /**
     * validate emptiness
     */
    public static void require(String str, String filedName) {
        if (StringUtils.isEmpty(str)) {
            throw new ValidatorException(filedName + " cannot be empty");
        }
    }

    /**
     * validate length
     */
    public static void length(String str, String fieldName, int min, int max) {
        int length = 0;
        if (!StringUtils.isEmpty(str)) {
            length = str.length();
        }
        if (length < min || length > max) {
            throw new ValidatorException(fieldName + " should be between " + min + " and " + max + " characters");
        }
    }
}
