package com.investment.findfriend.domain.feed.service;

import com.investment.findfriend.domain.feed.exception.FileException;
import com.investment.findfriend.domain.feed.presentation.dto.response.FeedResponse;
import com.investment.findfriend.domain.feed.repository.FeedRepository;
import com.investment.findfriend.domain.friend.domain.Friend;
import com.investment.findfriend.domain.friend.exception.FriendNotFoundException;
import com.investment.findfriend.domain.friend.repository.FriendRepository;
import com.investment.findfriend.domain.user.domain.User;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GetFeedListService {

    private final FeedRepository feedRepository;
    private final FriendRepository friendRepository;
    private final ImageEncoder imageEncoder;

    @Transactional
    public ResponseEntity<List<FeedResponse>> execute(Long friendId) {
        Friend friend = friendRepository.findById(friendId).orElseThrow(
                () -> FriendNotFoundException.EXCEPTION
        );
        return ResponseEntity.ok(feedRepository.findAllByFriend(friend).stream()
                .map(feed -> {
                    try {
                        return FeedResponse.builder()
                                .content(feed.getContent())
                                .resource(imageEncoder.encode(feed.getUrl()))
                                .users(feed.getTags().stream().map(User::getName).toList())
                                .build();
                    } catch (Exception e) {
                        throw FileException.EXCEPTION;
                    }
                }).toList());
    }
}
