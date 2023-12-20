package com.investment.findfriend.domain.feed.exception;

import com.investment.findfriend.global.error.exception.ErrorCode;
import com.investment.findfriend.global.error.exception.FindFriendException;

public class FileSaveException extends FindFriendException {
    public static final FileSaveException EXCEPTION = new FileSaveException(ErrorCode.FILE_SAVE_ERROR);
    public FileSaveException(ErrorCode errorCode) {
        super(errorCode);
    }
}
