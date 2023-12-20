package com.investment.findfriend.domain.feed.service;

import com.investment.findfriend.domain.auth.exception.UserNotFoundException;
import com.investment.findfriend.domain.auth.service.FileSaveUtil;
import com.investment.findfriend.domain.feed.domain.Feed;
import com.investment.findfriend.domain.feed.exception.FileNotFoundException;
import com.investment.findfriend.domain.feed.exception.UnAuthorizedException;
import com.investment.findfriend.domain.feed.presentation.dto.request.PostFeedRequest;
import com.investment.findfriend.domain.feed.presentation.dto.response.FeedResponse;
import com.investment.findfriend.domain.feed.repository.FeedRepository;
import com.investment.findfriend.domain.friend.exception.FriendNotFoundException;
import com.investment.findfriend.domain.friend.repository.FriendRepository;
import com.investment.findfriend.domain.user.domain.User;
import com.investment.findfriend.domain.user.domain.type.Authority;
import com.investment.findfriend.domain.user.repository.UserRepository;
import com.investment.findfriend.global.jwt.util.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostFeedService {

    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;
    private final FriendRepository friendRepository;
    private final FeedRepository feedRepository;
    private final FileSaveUtil fileSaveUtil;

    public ResponseEntity<FeedResponse> execute(PostFeedRequest request, HttpServletRequest httpServletRequest) {
        if (request.getFile().isEmpty()) {
            throw FileNotFoundException.EXCEPTION;
        }
        Optional<User> user = userRepository.findByEmail(jwtUtil.extractEmail(httpServletRequest));
        if (user.isEmpty() || user.get().getAuthority() != Authority.ROLE_ADMIN) {
            throw UnAuthorizedException.EXCEPTION;
        }

        String url = fileSaveUtil.save(request.getFile());

        List<User> userTagsList = request.getUserIds().stream()
                        .map(id -> userRepository.findById(id).orElseThrow(
                                () -> UserNotFoundException.EXCEPTION
                        )).toList();

        feedRepository.save(Feed.builder()
                .content(request.getContent())
                .friend(friendRepository.findById(request.getFriendId()).orElseThrow(
                        () -> FriendNotFoundException.EXCEPTION
                ))
                .tags(userTagsList)
                .build());
        return ResponseEntity.ok(FeedResponse.builder()
                .url(url)
                .build());
    }
}
