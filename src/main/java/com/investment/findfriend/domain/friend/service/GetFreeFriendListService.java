package com.investment.findfriend.domain.friend.service;

import com.investment.findfriend.domain.friend.domain.type.Authority;
import com.investment.findfriend.domain.friend.presentation.dto.response.FriendResponse;
import com.investment.findfriend.domain.friend.repository.FriendRepository;
import com.investment.findfriend.global.properties.ServerProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetFreeFriendListService {

    private final FriendRepository friendRepository;
    private final ServerProperties serverProperties;

    public ResponseEntity<List<FriendResponse>> execute() {
        return ResponseEntity.ok(friendRepository.findByAuthorityIn(List.of(Authority.ROLE_ANNOUNCE, Authority.ROLE_FREE)).stream()
                .map(friend -> FriendResponse.builder()
                        .id(friend.getId())
                        .authority(friend.getAuthority())
                        .statusMessage(friend.getStatusMessage())
                        .name(friend.getName())
                        .url(serverProperties.getUrl() + "/file?fileId=" + friend.getFile().getId())
                        .build())
                .toList());
    }
}
