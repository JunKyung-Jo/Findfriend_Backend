package com.investment.findfriend.domain.auth.exception;

import com.investment.findfriend.global.error.exception.ErrorCode;
import com.investment.findfriend.global.error.exception.FindFriendException;

public class UserNotFoundException extends FindFriendException {
    public static final UserNotFoundException EXCEPTION = new UserNotFoundException(ErrorCode.USER_NOT_FOUND);

    public UserNotFoundException(ErrorCode errorCode) {
        super(errorCode);
    }
}
