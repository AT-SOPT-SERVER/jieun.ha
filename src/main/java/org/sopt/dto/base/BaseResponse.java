package org.sopt.dto.base;

import org.sopt.dto.type.ErrorMessage;
import org.sopt.dto.type.SuccessMessage;

import java.util.Collections;

public class BaseResponse<T> {
    private final int code;
    private final String message;
    private T data;

    public BaseResponse(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public BaseResponse(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public T getData() {
        return data;
    }

    public static <T> BaseResponse<T> success(SuccessMessage success) {
        return new BaseResponse<>(success.getHttpStatusCode(), success.getMessage(), (T) Collections.emptyMap());
    }

    public static <T> BaseResponse<T> success(SuccessMessage success, T data) {
        return new BaseResponse<>(success.getHttpStatusCode(), success.getMessage(), data);
    }

    public static <T> BaseResponse<T> error(ErrorMessage error) {
        return new BaseResponse<>(error.getHttpStatusCode(), error.getMessage(), (T) Collections.emptyMap());
    }
}
