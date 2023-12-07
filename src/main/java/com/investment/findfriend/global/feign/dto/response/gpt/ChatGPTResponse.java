package com.investment.findfriend.global.feign.dto.response.gpt;

import lombok.Getter;

import java.util.List;

@Getter
public class ChatGPTResponse {
    private List<GeneratedMessage> choices;
}
