package org.sopt.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sopt.dto.base.BaseResponse;
import org.sopt.dto.type.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

@RestControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(value = MissingRequestHeaderException.class)
    public ResponseEntity<BaseResponse<?>> handleMissingRequestHeaderException(MissingRequestHeaderException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(BaseResponse.error(ErrorMessage.INVALID_HEADER_ERROR));
    }

    @ExceptionHandler(value = NoHandlerFoundException.class)
    public ResponseEntity<BaseResponse<?>> handleNotFoundException(NoHandlerFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(BaseResponse.error(ErrorMessage.NOT_FOUND_ERROR));
    }

    @ExceptionHandler(value = HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<BaseResponse<?>> handleMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED)
                .body(BaseResponse.error(ErrorMessage.METHOD_NOT_ALLOWED));
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<BaseResponse<?>> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(BaseResponse.error(ErrorMessage.METHOD_ARGUMENT_ERROR));
    }

    @ExceptionHandler(value = CustomException.class)
    public ResponseEntity<BaseResponse<?>> handleCustomException(CustomException e) {
        log.error("CustomException occurred: {}", e.getErrorMessage());
        return ResponseEntity.status(e.getHttpStatusCode())
                .body(BaseResponse.error(e.getErrorMessage()));
    }

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<BaseResponse<?>> handleGeneralException(Exception e) {
        log.error("Internal Server Error : {} - {}", e.getClass(), e.getMessage(), e);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(BaseResponse.error(ErrorMessage.INTERNAL_SERVER_ERROR));
    }
}
