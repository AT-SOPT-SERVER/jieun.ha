package org.sopt.exception;

import org.sopt.dto.type.ErrorMessage;

public class CustomException extends RuntimeException {
    private final ErrorMessage errorMessage;

    public CustomException(ErrorMessage errorMessage) {
        super(errorMessage.getMessage());
        this.errorMessage = errorMessage;
    }

    public int getHttpStatusCode() {
        return errorMessage.getHttpStatusCode();
    }

    public ErrorMessage getErrorMessage() {
        return errorMessage;
    }
}
