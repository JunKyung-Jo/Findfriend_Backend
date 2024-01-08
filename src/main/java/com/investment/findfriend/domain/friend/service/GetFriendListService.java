package com.investment.findfriend.domain.friend.service;

import com.investment.findfriend.domain.auth.exception.UserNotFoundException;
import com.investment.findfriend.domain.friend.domain.Friend;
import com.investment.findfriend.domain.friend.domain.type.Authority;
import com.investment.findfriend.domain.friend.presentation.dto.response.FriendResponse;
import com.investment.findfriend.domain.friend.repository.FriendRepository;
import com.investment.findfriend.domain.user.domain.User;
import com.investment.findfriend.domain.user.repository.UserRepository;
import com.investment.findfriend.global.jwt.util.JwtUtil;
import com.investment.findfriend.global.properties.ServerProperties;
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
    private final ServerProperties serverProperties;

    public ResponseEntity<List<FriendResponse>> execute(HttpServletRequest httpServletRequest) {
        // 가입된 유저인지 확인
        User user = userRepository.findByEmail(jwtUtil.extractEmail(httpServletRequest)).orElseThrow(
                () -> UserNotFoundException.EXCEPTION
        );

        // 공지, 무료, 또는 본인이 만든 커스텀 봇들만 모두 가져오기
        List<Friend> friendList = friendRepository.findByAuthorityInAndUserOrAuthorityIn(
                List.of(Authority.ROLE_CUSTOM),
                user,
                List.of(Authority.ROLE_ANNOUNCE, Authority.ROLE_FREE)
        );

        // map 을 통해 List 로 return
        return ResponseEntity.ok(friendList.stream()
                .map(friend -> FriendResponse.builder()
                        .id(friend.getId())
                        .authority(friend.getAuthority())
                        .statusMessage(friend.getStatusMessage())
                        .name(friend.getName())
                        .url(friend.getFile() != null ? serverProperties.getUrl() + "/file?fileId=" + friend.getFile().getId() : null)
                        .build())
                .toList());

    }
}