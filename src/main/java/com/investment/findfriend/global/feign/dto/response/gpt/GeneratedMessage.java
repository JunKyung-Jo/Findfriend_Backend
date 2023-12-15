package com.investment.findfriend.global.feign.dto.response.gpt;

import com.investment.findfriend.global.feign.dto.request.gpt.ChatGPTMessage;
import lombok.Getter;

@Getter
public class GeneratedMessage {
    private ChatGPTMessage message;
}
