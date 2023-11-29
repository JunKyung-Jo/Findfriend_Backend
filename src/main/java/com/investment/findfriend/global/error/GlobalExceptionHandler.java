package com.investment.findfriend.global.error;

import com.investment.findfriend.global.error.exception.ErrorCode;
import com.investment.findfriend.global.error.exception.FindFriendException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler(FindFriendException.class)
    public ResponseEntity<ErrorResponse> handleGlobal(FindFriendException e) {
        final ErrorCode errorCode = e.getErrorCode();
        log.error(
                "\n" + "{\n" +
                        "\t\"status\": " + errorCode.getStatus() + '\"' +
                        ",\n\t\"message\": \"" + errorCode.getMessage() + '\"' +
                        "\n}"
        );
        return new ResponseEntity<>(
                new ErrorResponse(
                        errorCode.getStatus(),
                        errorCode.getMessage()),
                HttpStatus.valueOf(errorCode.getStatus()));
    }
}
