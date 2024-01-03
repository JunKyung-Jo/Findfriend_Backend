package com.investment.findfriend.global.error.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum ErrorCode {
    USER_NOT_FOUND(404, "유저를 찾을 수 없습니다"),
    LOGGED_OUT_TOKEN(403, "로그아웃 되어 사용할 수 없는 토큰입니다"),
    TOKEN_NOT_FOUND(404, "토큰이 존재하지 않습니다"),
    REFRESH_TOKEN_NOT_FOUND(404, "토큰이 DB에 존재하지 않습니다"),
    FRIEND_NOT_FOUND(404, "친구를 찾을 수 없습니다"),
    FILE_NOT_FOUND(404, "이미지가 존재하지 않습니다"),
    FEED_NOT_FOUND(404, "피드가 존재하지 않습니다"),
    UNAUTHORIZED(403, "권한이 올바르지 않습니다"),
    FILE_ERROR(500, "파일 오류"),
    BAD_REQUEST(400, "잘못된 요청입니다");

    private final int status;
    private final String message;
}
