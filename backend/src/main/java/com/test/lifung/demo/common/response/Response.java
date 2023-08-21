package com.test.lifung.demo.common.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Response entity.
 *
 * @author BMK
 * @since 20.08.2023
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Response {
    /** Response message. */
    private String msg;

    /** Status code */
    private int code = 200;

    /** Response data */
    private Object data;

    public static Response ok(String message) {
        return new Response(message, 200, null);
    }

    public static Response ok(String message, Object data) {
        return new Response(message, 200, data);
    }

    public static Response error(String message) {
        return new Response(message, 400, null);
    }
}
