package com.investment.findfriend.global.feign.dto.request.gpt;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ChatGPTRequest {
    private String model;
    private String prompt;
    private int temperature;
    private int max_tokens;
}
