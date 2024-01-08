package com.investment.findfriend.domain.chat.service;

import com.investment.findfriend.domain.auth.exception.UserNotFoundException;
import com.investment.findfriend.domain.chat.domain.Chat;
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

@Service
@RequiredArgsConstructor
public class GetListChatService {
    private final UserRepository userRepository;
    private final FriendRepository friendRepository;
    private final ChatRepository chatRepository;
    private final JwtUtil jwtUtil;

    public ResponseEntity<List<ChatResponse>> execute(Long id, HttpServletRequest httpServletRequest) {
        // 가입된 유저 확인
        User user = userRepository.findByEmail(jwtUtil.extractEmail(httpServletRequest)).orElseThrow(
                () -> UserNotFoundException.EXCEPTION
        );

        // 친구 확인
        Friend friend = friendRepository.findById(id).orElseThrow(
                () -> FriendNotFoundException.EXCEPTION
        );

        // 채팅에 유저와 친구가 같은 것들만 모두 찾기
        List<Chat> chatList = chatRepository.findAllByUserAndFriend(user, friend);
        // 시간순 정렬
        chatList.sort(Comparator.comparing(Chat::getTimestamp));

        // map 을 통해 List 형태로 return
        return ResponseEntity.ok(
                chatList.stream()
                        .map(chat -> ChatResponse.builder()
                                .userMessage(chat.getUserMessage())
                                .replyMessage(chat.getReplyMessage())
                                .timestamp(chat.getTimestamp())
                                .build())
                        .toList()
        );

    }
}
