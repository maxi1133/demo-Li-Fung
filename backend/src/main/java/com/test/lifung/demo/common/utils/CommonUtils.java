package com.test.lifung.demo.common.utils;

import com.test.lifung.demo.constant.CommonConstants;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.util.Assert;

/**
 * Common utility.
 *
 * @author BMK
 * @since 20.08.2023
 */
public class CommonUtils {
    /**
     * Gen random key with 8 chars.
     *
     * @return random key.
     */
    public static String genRandomKey() {
        return RandomStringUtils.randomAlphanumeric(10);
    }

    /**
     * Get current user id from header.
     *
     * @param request request.
     * @return user id.
     */
    public static String getUserId(HttpServletRequest request) {
        final String userId = request.getHeader(CommonConstants.USER_ID_HEADER);
        Assert.notNull(userId, "User id in header is not existed.");
        return userId;
    }
}
