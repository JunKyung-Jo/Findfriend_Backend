package com.investment.findfriend.domain.friend.presentation.dto.response;

import com.investment.findfriend.domain.friend.domain.type.Authority;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class FriendResponse {
    private Long id;
    private String name;
    private String statusMessage;
    private Authority authority;
    private String url;
}
