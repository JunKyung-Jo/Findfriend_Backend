package com.investment.findfriend.global.error.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum ErrorCode {
    USER_NOT_FOUND(404, "유저를 찾을 수 없습니다"),
    LOGGED_OUT_TOKEN(403, "로그아웃 되어 사용할 수 없는 토큰입니다");

    private final int status;
    private final String message;
}
