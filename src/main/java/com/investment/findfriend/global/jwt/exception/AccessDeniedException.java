package com.investment.findfriend.global.jwt.exception;

import com.investment.findfriend.global.error.exception.ErrorCode;
import com.investment.findfriend.global.error.exception.FindFriendException;

public class AccessDeniedException extends FindFriendException {
    public static final AccessDeniedException EXCEPTION = new AccessDeniedException(ErrorCode.ACCESS_DENIED);

    public AccessDeniedException(ErrorCode errorCode) {
        super(errorCode);
    }
}
