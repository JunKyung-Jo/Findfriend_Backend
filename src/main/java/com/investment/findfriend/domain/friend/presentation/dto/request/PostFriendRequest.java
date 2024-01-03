package com.investment.findfriend.domain.friend.presentation.dto.request;

import com.investment.findfriend.domain.friend.domain.type.Personality;
import lombok.Getter;

import java.util.List;

@Getter
public class PostFriendRequest {
    private String name;
    private List<Personality> personalities;
    private String statusMessage;
}
