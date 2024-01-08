package com.investment.findfriend.domain.chat.service;

import com.investment.findfriend.domain.auth.exception.UserNotFoundException;
import com.investment.findfriend.domain.chat.domain.Chat;
import com.investment.findfriend.domain.chat.presentation.dto.request.ChatRequest;
import com.investment.findfriend.domain.chat.presentation.dto.response.GenerateChatResponse;
import com.investment.findfriend.domain.chat.repository.ChatRepository;
import com.investment.findfriend.domain.friend.exception.FriendNotFoundException;
import com.investment.findfriend.domain.friend.repository.FriendRepository;
import com.investment.findfriend.domain.user.domain.User;
import com.investment.findfriend.domain.user.repository.UserRepository;
import com.investment.findfriend.global.feign.dto.request.gpt.ChatGPTMessage;
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
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostChatService {
    private final UserRepository userRepository;
    private final ChatRepository chatRepository;
    private final FriendRepository friendRepository;
    private final JwtUtil jwtUtil;
    private final ChatGPTClient chatGPTClient;
    private final ChatGPTProperties chatGPTProperties;

    public ResponseEntity<GenerateChatResponse> execute(ChatRequest request, HttpServletRequest httpServletRequest) {
        // 가입된 유저인지 확인
        User user = userRepository.findByEmail(jwtUtil.extractEmail(httpServletRequest)).orElseThrow(
                () -> UserNotFoundException.EXCEPTION
        );

        // ChatGPT API 를 통해 응답 가져오기
        ChatGPTResponse response = chatGPTClient.getChatGPTResponse(ChatGPTRequest.builder()
                .model(chatGPTProperties.getModel())
                .temperature(chatGPTProperties.getTemperature())
                .messages(List.of(ChatGPTMessage.builder()
                        .role("user")
                        .content(request.getText())
                        .build()))
                .build(), "Bearer " + chatGPTProperties.getApiKey());

        // 질문과 응답을 유저로 묶어 저장
        chatRepository.save(
                Chat.builder()
                        .user(user)
                        .userMessage(request.getText())
                        .friend(friendRepository.findById(request.getFriendId()).orElseThrow(
                                () -> FriendNotFoundException.EXCEPTION
                        ))
                        .replyMessage(response.getChoices().get(0).getMessage().getContent())
                        .timestamp(LocalDateTime.now())
                        .build()
        );

        // 응답 return
        return ResponseEntity.ok(GenerateChatResponse.builder()
                .response(response.getChoices().get(0).getMessage().getContent())
                .build());
    }
}
