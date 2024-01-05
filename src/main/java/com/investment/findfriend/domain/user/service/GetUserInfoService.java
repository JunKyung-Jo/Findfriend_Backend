package com.investment.findfriend.domain.user.service;

import com.investment.findfriend.domain.auth.exception.UserNotFoundException;
import com.investment.findfriend.domain.friend.presentation.dto.response.FriendResponse;
import com.investment.findfriend.domain.user.domain.User;
import com.investment.findfriend.domain.user.presentation.dto.response.UserResponse;
import com.investment.findfriend.domain.user.repository.UserRepository;
import com.investment.findfriend.global.jwt.util.JwtUtil;
import com.investment.findfriend.global.properties.ServerProperties;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetUserInfoService {

    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;
    private final ServerProperties serverProperties;

    @Transactional
    public ResponseEntity<UserResponse> execute(HttpServletRequest httpServletRequest) {
        User user = userRepository.findByEmail(jwtUtil.extractEmail(httpServletRequest)).orElseThrow(
                () -> UserNotFoundException.EXCEPTION
        );
        return ResponseEntity.ok(UserResponse.builder()
                .name(user.getName())
                .statusMessage(user.getStatusMessage())
                .url(serverProperties.getUrl() + "/file?fileId=" + user.getFile().getId())
                .friends(user.getFriends().stream()
                        .map(friend -> FriendResponse.builder()
                                .id(friend.getId())
                                .name(friend.getName())
                                .statusMessage(friend.getStatusMessage())
                                .authority(friend.getAuthority())
                                .url(serverProperties.getUrl() + "/file?fileId=" + friend.getFile().getId())
                                .build())
                        .toList())
                .build());
    }
}
