package com.investment.findfriend.domain.chat.service;

import com.investment.findfriend.domain.auth.exception.UserNotFoundException;
import com.investment.findfriend.domain.chat.domain.Chat;
import com.investment.findfriend.domain.chat.presentation.dto.request.ChatRequest;
import com.investment.findfriend.domain.chat.presentation.dto.response.GenerateChatResponse;
import com.investment.findfriend.domain.chat.repository.ChatRepository;
import com.investment.findfriend.domain.user.domain.User;
import com.investment.findfriend.domain.user.repository.UserRepository;
import com.investment.findfriend.global.feign.dto.request.gpt.ChatGPTRequest;
import com.investment.findfriend.global.feign.dto.response.gpt.ChatGPTResponse;
import com.investment.findfriend.global.feign.gpt.ChatGPTClient;
import com.investment.findfriend.global.feign.properties.ChatGPTProperties;
import com.investment.findfriend.global.jwt.util.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class PostChatService {
    private final UserRepository userRepository;
    private final ChatRepository chatRepository;
    private final JwtUtil jwtUtil;
    private final ChatGPTClient chatGPTClient;
    private final ChatGPTProperties chatGPTProperties;

    public ResponseEntity<GenerateChatResponse> execute(ChatRequest request, HttpServletRequest httpServletRequest) {
        User user = userRepository.findByEmail(jwtUtil.extractEmail(httpServletRequest)).orElseThrow(
                () -> UserNotFoundException.EXCEPTION
        );

        ChatGPTResponse response = chatGPTClient.getChatGPTResponse(ChatGPTRequest.builder()
                .model(chatGPTProperties.getModel())
                .max_tokens(chatGPTProperties.getMax_tokens())
                .temperature(chatGPTProperties.getTemperature())
                .prompt(request.getText())
                .build(), chatGPTProperties.getApiKey());

        chatRepository.save(
                Chat.builder()
                        .user(user)
                        .userMessage(request.getText())
                        .replyMessage(response.getChoices().get(0).getText())
                        .timestamp(LocalDateTime.now())
                        .build()
        );

        return ResponseEntity.ok(GenerateChatResponse.builder()
                .response(response.getChoices().get(0).getText())
                .build());
    }
}
