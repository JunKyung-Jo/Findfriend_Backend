package com.investment.findfriend.domain.chat.presentation.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class ChatResponse {
    private String userMessage;
    private String replyMessage;
    private LocalDateTime timestamp;
}
