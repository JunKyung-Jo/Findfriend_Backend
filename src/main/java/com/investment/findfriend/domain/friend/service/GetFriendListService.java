package com.investment.findfriend.domain.friend.service;

import com.investment.findfriend.domain.auth.exception.UserNotFoundException;
import com.investment.findfriend.domain.friend.domain.Friend;
import com.investment.findfriend.domain.friend.domain.type.Authority;
import com.investment.findfriend.domain.friend.presentation.dto.response.FriendResponse;
import com.investment.findfriend.domain.friend.repository.FriendRepository;
import com.investment.findfriend.domain.user.domain.User;
import com.investment.findfriend.domain.user.repository.UserRepository;
import com.investment.findfriend.global.jwt.util.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetFriendListService {

    private final FriendRepository friendRepository;
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;

    public ResponseEntity<List<FriendResponse>> execute(HttpServletRequest httpServletRequest) {
        User user = userRepository.findByEmail(jwtUtil.extractEmail(httpServletRequest)).orElseThrow(
                () -> UserNotFoundException.EXCEPTION
        );

        List<Friend> friendList = friendRepository.findByAuthorityInAndUserOrAuthorityAndUserIsNull(
                List.of(Authority.ROLE_ANNOUNCE, Authority.ROLE_FREE),
                user,
                Authority.ROLE_CUSTOM
        );

        return ResponseEntity.ok(friendList.stream()
                .map(friend -> FriendResponse.builder()
                        .id(friend.getId())
                        .authority(friend.getAuthority())
                        .statusMessage(friend.getStatusMessage())
                        .name(friend.getName())
                        .build())
                .toList());
    }
}