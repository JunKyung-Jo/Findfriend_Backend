package com.investment.findfriend.domain.feed.exception;

import com.investment.findfriend.global.error.exception.ErrorCode;
import com.investment.findfriend.global.error.exception.FindFriendException;

public class FileNotFoundException extends FindFriendException {
    public static final FileNotFoundException EXCEPTION = new FileNotFoundException(ErrorCode.FILE_NOT_FOUND);
    public FileNotFoundException(ErrorCode errorCode) {
        super(errorCode);
    }
}
