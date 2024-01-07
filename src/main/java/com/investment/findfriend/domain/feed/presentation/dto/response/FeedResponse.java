package com.investment.findfriend.domain.feed.presentation.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class FeedResponse {
    private String name;
    private String content;
    private List<String> tags;
    private String url;
}
