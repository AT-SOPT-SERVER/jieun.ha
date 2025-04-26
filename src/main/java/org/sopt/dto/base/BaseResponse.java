package org.sopt.dto.base;

import java.util.LinkedHashMap;

public class BaseResponse<T> {
    private int code;
    private String message;
    private T data;

    public BaseResponse () {}

    public BaseResponse(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static <T> BaseResponse<T> success(T data) {
        if (data == null) {
            return new BaseResponse<>(200, "OK", (T) new LinkedHashMap<>());
        }
        return new BaseResponse<>(200, "OK", data);
    }

    public static <T> BaseResponse<T> fail(int code, String message) {
        return new BaseResponse<>(code, message, null);
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
