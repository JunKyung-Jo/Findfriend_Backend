package com.investment.findfriend.domain.user.presentation.dto.response;

import com.investment.findfriend.domain.friend.presentation.dto.response.FriendResponse;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class UserResponse {
    private String name;
    private String statusMessage;
    private String url;
    private List<FriendResponse> friends;
}
