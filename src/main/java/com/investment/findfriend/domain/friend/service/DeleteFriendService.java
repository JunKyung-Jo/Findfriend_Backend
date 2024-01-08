package com.investment.findfriend.domain.friend.service;

import com.investment.findfriend.domain.feed.repository.FeedRepository;
import com.investment.findfriend.domain.friend.domain.Friend;
import com.investment.findfriend.domain.friend.exception.FriendNotFoundException;
import com.investment.findfriend.domain.friend.repository.FriendRepository;
import com.investment.findfriend.domain.likes.repository.LikesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class DeleteFriendService {

    private final FriendRepository friendRepository;
    private final FeedRepository feedRepository;
    private final LikesRepository likesRepository;

    public ResponseEntity<String> execute(Long friendId) {
        Friend friend = friendRepository.findById(friendId).orElseThrow(
                () -> FriendNotFoundException.EXCEPTION
        );
        // 피드 내용 삭제
        friend.getFeed().stream().map(feed -> {
            likesRepository.deleteByFeedId(feed.getId());
            feedRepository.deleteById(feed.getId());
            return null;
        });
        // 친구 id 값을 통한 삭제
        friendRepository.deleteById(friendId);
        // 성공 여부 return
        return ResponseEntity.ok("success");
    }
}
