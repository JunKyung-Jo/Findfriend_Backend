package com.investment.findfriend.domain.user.service;

import com.investment.findfriend.domain.auth.exception.UserNotFoundException;
import com.investment.findfriend.domain.user.domain.User;
import com.investment.findfriend.domain.user.presentation.dto.response.UserResponse;
import com.investment.findfriend.domain.user.repository.UserRepository;
import com.investment.findfriend.global.jwt.util.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetUserInfoService {

    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;

    public ResponseEntity<UserResponse> execute(HttpServletRequest request) {
        User user = userRepository.findByEmail(jwtUtil.extractEmail(request)).orElseThrow(
                () -> UserNotFoundException.EXCEPTION
        );
        return ResponseEntity.ok(UserResponse.builder()
                .name(user.getName())
                .statusMessage(user.getStatusMessage())
                .build());
    }
}
