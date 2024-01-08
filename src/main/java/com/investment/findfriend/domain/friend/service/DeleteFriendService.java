package com.investment.findfriend.domain.friend.service;

import com.investment.findfriend.domain.feed.repository.FeedRepository;
import com.investment.findfriend.domain.friend.domain.Friend;
import com.investment.findfriend.domain.friend.exception.FriendNotFoundException;
import com.investment.findfriend.domain.friend.repository.FriendRepository;
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

    public ResponseEntity<String> execute(Long friendId) {
        Friend friend = friendRepository.findById(friendId).orElseThrow(
                () -> FriendNotFoundException.EXCEPTION
        );
        // 피드 내용 삭제
        friend.getFeed().forEach(feed -> feedRepository.deleteById(feed.getId()));
        // 친구 id 값을 통한 삭제
        friendRepository.deleteById(friendId);
        // 성공 여부 return
        return ResponseEntity.ok("success");
    }
}
