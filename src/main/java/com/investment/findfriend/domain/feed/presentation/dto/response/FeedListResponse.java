package com.investment.findfriend.domain.feed.presentation.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class FeedListResponse {
    private Long id;
    private String url;
}
