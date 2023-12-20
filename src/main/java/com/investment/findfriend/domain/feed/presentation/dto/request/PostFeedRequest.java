package com.investment.findfriend.domain.feed.presentation.dto.request;

import lombok.Getter;
import java.util.List;

@Getter
public class PostFeedRequest {
    private String content;
    private Long friendId;
    private List<Long> userIds;
}
