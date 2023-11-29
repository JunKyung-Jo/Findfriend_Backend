package com.investment.findfriend.global.jwt.exception;

import com.investment.findfriend.global.error.exception.ErrorCode;
import com.investment.findfriend.global.error.exception.FindFriendException;

public class LoggedOutAccessTokenException extends FindFriendException {

    public static final LoggedOutAccessTokenException EXCEPTION = new LoggedOutAccessTokenException(ErrorCode.LOGGED_OUT_TOKEN);

    public LoggedOutAccessTokenException(ErrorCode errorCode) {
        super(errorCode);
    }
}
