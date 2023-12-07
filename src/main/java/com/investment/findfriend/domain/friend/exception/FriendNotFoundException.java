package com.investment.findfriend.domain.friend.exception;

import com.investment.findfriend.global.error.exception.ErrorCode;
import com.investment.findfriend.global.error.exception.FindFriendException;

public class FriendNotFoundException extends FindFriendException {
    public static final FriendNotFoundException EXCEPTION = new FriendNotFoundException(ErrorCode.FRIEND_NOT_FOUND);
    public FriendNotFoundException(ErrorCode errorCode) {
        super(errorCode);
    }
}
