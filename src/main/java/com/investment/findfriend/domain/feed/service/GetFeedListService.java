package com.investment.findfriend.domain.feed.service;

import com.investment.findfriend.domain.feed.presentation.dto.response.FeedListResponse;
import com.investment.findfriend.domain.feed.repository.FeedRepository;
import com.investment.findfriend.domain.friend.domain.Friend;
import com.investment.findfriend.domain.friend.exception.FriendNotFoundException;
import com.investment.findfriend.domain.friend.repository.FriendRepository;
import com.investment.findfriend.global.properties.ServerProperties;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetFeedListService {

    private final ServerProperties serverProperties;
    private final FeedRepository feedRepository;
    private final FriendRepository friendRepository;

    @Transactional
    public ResponseEntity<List<FeedListResponse>> execute(Long friendId) {
        // 친구 존재하는지 확인
        Friend friend = friendRepository.findById(friendId).orElseThrow(
                () -> FriendNotFoundException.EXCEPTION
        );
        // 그 친구에 해당하는 값만 return
        return ResponseEntity.ok(feedRepository.findAllByFriend(friend).stream()
                .map(feed -> FeedListResponse.builder()
                        .id(feed.getId())
                        .url(serverProperties.getUrl() + "/file?fileId=" + feed.getFile().getId())
                        .build()).toList());
    }
}
