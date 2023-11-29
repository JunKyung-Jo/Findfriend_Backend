package com.investment.findfriend.global.error.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class FindFriendException extends RuntimeException{
    private final ErrorCode errorCode;
}
