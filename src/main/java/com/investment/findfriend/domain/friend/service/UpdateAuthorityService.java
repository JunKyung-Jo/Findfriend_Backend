package com.investment.findfriend.domain.friend.service;

import com.investment.findfriend.domain.auth.exception.UserNotFoundException;
import com.investment.findfriend.domain.friend.domain.Friend;
import com.investment.findfriend.domain.friend.exception.FriendNotFoundException;
import com.investment.findfriend.domain.friend.presentation.dto.request.UpdateAuthorityRequest;
import com.investment.findfriend.domain.friend.repository.FriendRepository;
import com.investment.findfriend.domain.user.domain.User;
import com.investment.findfriend.domain.user.domain.type.Authority;
import com.investment.findfriend.domain.user.repository.UserRepository;
import com.investment.findfriend.global.jwt.util.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UpdateAuthorityService {

    private final UserRepository userRepository;
    private final FriendRepository friendRepository;
    private final JwtUtil jwtUtil;

    public ResponseEntity<String> execute(UpdateAuthorityRequest request, HttpServletRequest httpServletRequest) {
        User user = userRepository.findByEmail(jwtUtil.extractEmail(httpServletRequest)).orElseThrow(
                () -> UserNotFoundException.EXCEPTION
        );

        if (user.getAuthority() == Authority.ROLE_ADMIN) {
            Friend friend = friendRepository.findById(request.getId()).orElseThrow(
                    () -> FriendNotFoundException.EXCEPTION
            );
            friend.setAuthority(request.getAuthority());
            friendRepository.save(friend);
            return ResponseEntity.ok("success");
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("ADMIN만 가능한 요청입니다");
    }
}
