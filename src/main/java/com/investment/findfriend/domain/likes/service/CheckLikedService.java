package com.investment.findfriend.domain.likes.service;

import com.investment.findfriend.domain.auth.exception.UserNotFoundException;
import com.investment.findfriend.domain.feed.domain.Feed;
import com.investment.findfriend.domain.feed.exception.FeedNotFoundException;
import com.investment.findfriend.domain.feed.repository.FeedRepository;
import com.investment.findfriend.domain.likes.presentation.dto.response.LikesResponse;
import com.investment.findfriend.domain.likes.repository.LikesRepository;
import com.investment.findfriend.domain.user.domain.User;
import com.investment.findfriend.domain.user.repository.UserRepository;
import com.investment.findfriend.global.jwt.util.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CheckLikedService {

    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;
    private final FeedRepository feedRepository;
    private final LikesRepository likesRepository;

    public ResponseEntity<LikesResponse> execute(Long feedId, HttpServletRequest httpServletRequest) {
        User user = userRepository.findByEmail(jwtUtil.extractEmail(httpServletRequest)).orElseThrow(
                () -> UserNotFoundException.EXCEPTION
        );
        Feed feed = feedRepository.findById(feedId).orElseThrow(
                () -> FeedNotFoundException.EXCEPTION
        );
        return ResponseEntity.ok(LikesResponse.builder()
                .isLiked(likesRepository.existsByUserAndFeed(user, feed))
                .count(likesRepository.countByFeedId(feedId))
                .build());
    }
}
