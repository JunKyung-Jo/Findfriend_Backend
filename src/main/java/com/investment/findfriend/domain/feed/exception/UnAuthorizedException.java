package com.investment.findfriend.domain.feed.exception;

import com.investment.findfriend.global.error.exception.ErrorCode;
import com.investment.findfriend.global.error.exception.FindFriendException;

public class UnAuthorizedException extends FindFriendException {
    public static final UnAuthorizedException EXCEPTION = new UnAuthorizedException(ErrorCode.UNAUTHORIZED);
    public UnAuthorizedException(ErrorCode errorCode) {
        super(errorCode);
    }
}
