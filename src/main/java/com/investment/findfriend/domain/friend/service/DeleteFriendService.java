package com.investment.findfriend.domain.friend.service;

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

    public ResponseEntity<String> execute(Long friendId) {
        // 친구 id 값을 통한 삭제
        friendRepository.deleteById(friendId);
        // 성공 여부 return
        return ResponseEntity.ok("success");
    }
}
