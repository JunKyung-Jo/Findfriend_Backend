package com.investment.findfriend.global.jwt.exception;

import com.investment.findfriend.global.error.exception.ErrorCode;
import com.investment.findfriend.global.error.exception.FindFriendException;

public class TokenNotFoundException extends FindFriendException {
    public static final TokenNotFoundException EXCEPTION = new TokenNotFoundException(ErrorCode.TOKEN_NOT_FOUND);
    public TokenNotFoundException(ErrorCode errorCode) {
        super(errorCode);
    }
}
