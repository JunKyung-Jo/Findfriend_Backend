package com.investment.findfriend.domain.likes.presentation.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class LikesResponse {
    private Boolean isLiked;
    private Long count;
}
