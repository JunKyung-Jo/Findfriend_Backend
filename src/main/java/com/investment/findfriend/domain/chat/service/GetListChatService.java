package com.investment.findfriend.domain.chat.service;

import com.investment.findfriend.domain.auth.exception.UserNotFoundException;
import com.investment.findfriend.domain.chat.domain.Chat;
import com.investment.findfriend.domain.chat.presentation.dto.request.GetListChatRequest;
import com.investment.findfriend.domain.chat.presentation.dto.response.ChatResponse;
import com.investment.findfriend.domain.chat.repository.ChatRepository;
import com.investment.findfriend.domain.friend.domain.Friend;
import com.investment.findfriend.domain.friend.exception.FriendNotFoundException;
import com.investment.findfriend.domain.friend.repository.FriendRepository;
import com.investment.findfriend.domain.user.domain.User;
import com.investment.findfriend.domain.user.repository.UserRepository;
import com.investment.findfriend.global.jwt.util.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GetListChatService {
    private final UserRepository userRepository;
    private final FriendRepository friendRepository;
    private final ChatRepository chatRepository;
    private final JwtUtil jwtUtil;

    public ResponseEntity<List<ChatResponse>> execute(GetListChatRequest request, HttpServletRequest httpServletRequest) {
        User user = userRepository.findByEmail(jwtUtil.extractEmail(httpServletRequest)).orElseThrow(
                () -> UserNotFoundException.EXCEPTION
        );

        Friend friend = friendRepository.findById(request.getFriendId()).orElseThrow(
                () -> FriendNotFoundException.EXCEPTION
        );

        List<Chat> chatList = chatRepository.findAllByUserAndFriend(user, friend);
        chatList.sort(Comparator.comparing(Chat::getTimestamp));

        return ResponseEntity.ok(
                chatList.stream()
                        .map(chat -> ChatResponse.builder()
                                .userMessage(chat.getUserMessage())
                                .replyMessage(chat.getReplyMessage())
                                .timestamp(chat.getTimestamp())
                                .build())
                        .collect(Collectors.toList())
        );

    }
}
