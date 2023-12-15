package com.investment.findfriend.global.feign.dto.request.gpt;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ChatGPTMessage {
    private String role;
    private String content;
}
