package com.investment.findfriend.domain.feed.exception;

import com.investment.findfriend.global.error.exception.ErrorCode;
import com.investment.findfriend.global.error.exception.FindFriendException;

public class FeedNotFoundException extends FindFriendException {
    public static final FeedNotFoundException EXCEPTION = new FeedNotFoundException(ErrorCode.FEED_NOT_FOUND);

    public FeedNotFoundException(ErrorCode errorCode) {
        super(errorCode);
    }
}
