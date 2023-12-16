package com.investment.findfriend.domain.chat.presentation.dto.request;

import lombok.Getter;

@Getter
public class ChatRequest {
    private String text;
    private Long friendId;
}
