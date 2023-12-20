package com.investment.findfriend.domain.feed.exception;

import com.investment.findfriend.global.error.exception.ErrorCode;
import com.investment.findfriend.global.error.exception.FindFriendException;

public class FileException extends FindFriendException {
    public static final FileException EXCEPTION = new FileException(ErrorCode.FILE_ERROR);
    public FileException(ErrorCode errorCode) {
        super(errorCode);
    }
}
