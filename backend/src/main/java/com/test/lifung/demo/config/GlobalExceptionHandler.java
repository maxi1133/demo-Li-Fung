package com.test.lifung.demo.config;

import com.test.lifung.demo.common.response.Response;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Global exception handlers.
 *
 * @author BMK
 * @since 20.08.2023
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Catch all exception from system.
     *
     * @param exception exception.
     * @return error message.
     */
    @ExceptionHandler({Exception.class})
    public Response handler(final Exception exception) {
        return Response.error(exception.getMessage());
    }
}
