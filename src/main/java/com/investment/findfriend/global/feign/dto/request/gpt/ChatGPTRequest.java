package com.investment.findfriend.global.feign.dto.request.gpt;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class ChatGPTRequest {
    private String model;
    private List<ChatGPTMessage> messages;
    private int temperature;
}
