package com.investment.findfriend.domain.auth.exception;

import com.investment.findfriend.global.error.exception.ErrorCode;
import com.investment.findfriend.global.error.exception.FindFriendException;

public class RefreshTokenNotFoundException extends FindFriendException {
    public static final RefreshTokenNotFoundException EXCEPTION = new RefreshTokenNotFoundException(ErrorCode.REFRESH_TOKEN_NOT_FOUND);
    public RefreshTokenNotFoundException(ErrorCode errorCode) {
        super(errorCode);
    }
}
