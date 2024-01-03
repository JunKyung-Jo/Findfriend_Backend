package com.investment.findfriend.domain.feed.service;

import com.investment.findfriend.domain.feed.presentation.dto.response.FeedListResponse;
import com.investment.findfriend.domain.feed.repository.FeedRepository;
import com.investment.findfriend.domain.friend.domain.Friend;
import com.investment.findfriend.domain.friend.exception.FriendNotFoundException;
import com.investment.findfriend.domain.friend.repository.FriendRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetFeedListService {

    @Value("${SERVER.URL}")
    private String serverURL;
    private final FeedRepository feedRepository;
    private final FriendRepository friendRepository;

    @Transactional
    public ResponseEntity<List<FeedListResponse>> execute(Long friendId) {
        Friend friend = friendRepository.findById(friendId).orElseThrow(
                () -> FriendNotFoundException.EXCEPTION
        );
        return ResponseEntity.ok(feedRepository.findAllByFriend(friend).stream()
                .map(feed -> FeedListResponse.builder()
                        .id(feed.getId())
                        .url(serverURL + "/image?feedId=" + feed.getId())
                        .build()).toList());
    }
}
