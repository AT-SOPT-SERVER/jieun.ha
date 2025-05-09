package org.sopt.dto.type;

import org.springframework.http.HttpStatus;

public enum ErrorMessage {
    // 400 Bad Request
    INVALID_HEADER_ERROR(HttpStatus.BAD_REQUEST, "유효하지 않은 헤더입니다."),
    INVALID_TITLE_ERROR(HttpStatus.BAD_REQUEST, "30자 이내의 제목을 입력해주세요."),
    DUPLICATE_TITLE_ERROR(HttpStatus.BAD_REQUEST, "중복된 게시글 제목입니다."),

    // 401 Unauthorized
    UNAUTHORIZED_ERROR(HttpStatus.UNAUTHORIZED, "인증되지 않은 사용자입니다."),

    // 404 Not Found
    NOT_FOUND_ERROR(HttpStatus.NOT_FOUND, "해당 리소스가 존재하지 않습니다."),

    // 405 Method Not Allowed
    METHOD_NOT_ALLOWED(HttpStatus.METHOD_NOT_ALLOWED, "지원하지 않는 HTTP 메소드입니다."),

    // 500 Internal Server Error
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "서버 내부 오류입니다.");

    private final HttpStatus status;
    private final String message;

    ErrorMessage(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public int getHttpStatusCode() {
        return status.value();
    }
}
