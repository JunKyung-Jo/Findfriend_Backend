package com.investment.findfriend.domain.feed.presentation.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class FeedResponse {
    private String content;
    private String resource;
    private List<String> users;
}
