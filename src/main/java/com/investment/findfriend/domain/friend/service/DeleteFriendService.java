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
        friendRepository.deleteById(friendId);
        return ResponseEntity.ok("success");
    }
}
