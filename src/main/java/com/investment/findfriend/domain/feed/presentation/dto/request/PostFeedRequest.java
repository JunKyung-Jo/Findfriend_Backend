package com.investment.findfriend.domain.feed.presentation.dto.request;

import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter
public class PostFeedRequest {
    private MultipartFile file;
    private String content;
    private Long friendId;
    private List<Long> userIds;
}
