package com.investment.findfriend.domain.likes.service;

import com.investment.findfriend.domain.auth.exception.UserNotFoundException;
import com.investment.findfriend.domain.likes.repository.LikesRepository;
import com.investment.findfriend.domain.user.domain.User;
import com.investment.findfriend.domain.user.repository.UserRepository;
import com.investment.findfriend.global.jwt.util.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class DeleteLikeService {

    private final UserRepository userRepository;
    private final LikesRepository likesRepository;
    private final JwtUtil jwtUtil;

    public ResponseEntity<String> execute(Long feedId, HttpServletRequest httpServletRequest) {
        // 가입된 유저인지 확인
        User user = userRepository.findByEmail(jwtUtil.extractEmail(httpServletRequest)).orElseThrow(
                () -> UserNotFoundException.EXCEPTION
        );
        // 좋아요 삭제
        likesRepository.deleteByFeedIdAndUserId(feedId, user.getId());
        // 성공
        return ResponseEntity.ok("success");
    }
}
