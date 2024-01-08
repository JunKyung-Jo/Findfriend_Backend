package com.investment.findfriend.domain.likes.service;

import com.investment.findfriend.domain.auth.exception.UserNotFoundException;
import com.investment.findfriend.domain.feed.domain.Feed;
import com.investment.findfriend.domain.feed.exception.FeedNotFoundException;
import com.investment.findfriend.domain.feed.repository.FeedRepository;
import com.investment.findfriend.domain.likes.domain.Likes;
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
public class FeedLikeService {

    private final FeedRepository feedRepository;
    private final LikesRepository likesRepository;
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;


    public ResponseEntity<String> execute(Long feedId, HttpServletRequest httpServletRequest) {
        // 가입된 유저 확인
        User user = userRepository.findByEmail(jwtUtil.extractEmail(httpServletRequest)).orElseThrow(
                () -> UserNotFoundException.EXCEPTION
        );
        // 게시물 찾기
        Feed feed = feedRepository.findById(feedId).orElseThrow(
                () -> FeedNotFoundException.EXCEPTION
        );
        // 좋아요 여부를 저장
        likesRepository.save(Likes.builder()
                .feed(feed)
                .user(user)
                .build());
        // 성공 여부 return
        return ResponseEntity.ok("success");
    }
}
