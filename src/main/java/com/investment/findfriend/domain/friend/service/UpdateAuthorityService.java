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
        // 가입된 유저인지 확인
        User user = userRepository.findByEmail(jwtUtil.extractEmail(httpServletRequest)).orElseThrow(
                () -> UserNotFoundException.EXCEPTION
        );

        // 권한이 ADMIN 인지 확인
        if (user.getAuthority() == Authority.ROLE_ADMIN) {
            // 친구를 찾고
            Friend friend = friendRepository.findById(request.getId()).orElseThrow(
                    () -> FriendNotFoundException.EXCEPTION
            );
            // 그 친구의 권한을 변경
            friend.setAuthority(request.getAuthority());
            // 변경한 내용 저장
            friendRepository.save(friend);
            // 성공 여부 return
            return ResponseEntity.ok("success");
        }
        // 권한 부족 처리
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("ADMIN만 가능한 요청입니다");
    }
}