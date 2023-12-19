package com.investment.findfriend.domain.auth.exception;

import com.investment.findfriend.global.error.exception.ErrorCode;
import com.investment.findfriend.global.error.exception.FindFriendException;

public class BadRequestException extends FindFriendException {
    public static final BadRequestException EXCEPTION = new BadRequestException(ErrorCode.BAD_REQUEST);
    public BadRequestException(ErrorCode errorCode) {
        super(errorCode);
    }
}
