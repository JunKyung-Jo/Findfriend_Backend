package com.investment.findfriend.domain.chat.service;

import com.investment.findfriend.domain.chat.presentation.dto.request.ChatRequest;
import com.investment.findfriend.domain.chat.presentation.dto.response.GenerateChatResponse;
import com.investment.findfriend.global.feign.dto.request.gpt.ChatGPTMessage;
import com.investment.findfriend.global.feign.dto.request.gpt.ChatGPTRequest;
import com.investment.findfriend.global.feign.dto.response.gpt.ChatGPTResponse;
import com.investment.findfriend.global.feign.gpt.ChatGPTClient;
import com.investment.findfriend.global.feign.properties.ChatGPTProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FreeChatService {

    private final ChatGPTClient chatGPTClient;
    private final ChatGPTProperties chatGPTProperties;

    public ResponseEntity<GenerateChatResponse> execute(ChatRequest request) {
        // 응답 생성
        ChatGPTResponse response = chatGPTClient.getChatGPTResponse(ChatGPTRequest.builder()
                .model(chatGPTProperties.getModel())
                .temperature(chatGPTProperties.getTemperature())
                .messages(List.of(ChatGPTMessage.builder()
                        .role("user")
                        .content(request.getText())
                        .build()))
                .build(), "Bearer " + chatGPTProperties.getApiKey());
        // 응답 값 return
        return ResponseEntity.ok(GenerateChatResponse.builder()
                .response(response.getChoices().get(0).getMessage().getContent())
                .build());
    }
}
