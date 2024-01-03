package com.investment.findfriend.domain.likes.service;

import com.investment.findfriend.domain.auth.exception.UserNotFoundException;
import com.investment.findfriend.domain.feed.repository.FeedRepository;
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
    private final FeedRepository feedRepository;
    private final JwtUtil jwtUtil;

    public ResponseEntity<String> execute(Long feedId, HttpServletRequest httpServletRequest) {
        User user = userRepository.findByEmail(jwtUtil.extractEmail(httpServletRequest)).orElseThrow(
                () -> UserNotFoundException.EXCEPTION
        );
        feedRepository.deleteByIdAndUser(feedId, user);
        return ResponseEntity.ok("success");
    }
}
