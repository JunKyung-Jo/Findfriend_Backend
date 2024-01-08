package com.investment.findfriend.domain.friend.service;

import com.investment.findfriend.domain.auth.exception.UserNotFoundException;
import com.investment.findfriend.domain.auth.service.FileSaveUtil;
import com.investment.findfriend.domain.file.domain.File;
import com.investment.findfriend.domain.file.repository.FileRepository;
import com.investment.findfriend.domain.friend.domain.Friend;
import com.investment.findfriend.domain.friend.domain.type.Authority;
import com.investment.findfriend.domain.friend.presentation.dto.request.PostFriendRequest;
import com.investment.findfriend.domain.friend.repository.FriendRepository;
import com.investment.findfriend.domain.user.domain.User;
import com.investment.findfriend.domain.user.repository.UserRepository;
import com.investment.findfriend.global.jwt.util.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class PostFriendService {

    private final FriendRepository friendRepository;
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;
    private final FileSaveUtil fileSaveUtil;
    private final FileRepository fileRepository;

    @Transactional
    public ResponseEntity<String> execute(PostFriendRequest request, MultipartFile file, HttpServletRequest httpServletRequest) {
        // 가입된 유저인지 확인
        User user = userRepository.findByEmail(jwtUtil.extractEmail(httpServletRequest))
                .orElseThrow(() -> UserNotFoundException.EXCEPTION);

        File savedFile = null;
        // 친구 이미지가 존재한다면 저장
        if (file != null && !file.isEmpty()) {
            savedFile = fileRepository.save(File.builder().path(fileSaveUtil.save(file)).build());
        }

        Friend friend = Friend.builder()
                .user(user)
                .statusMessage(request.getStatusMessage())
                .authority(Authority.ROLE_CUSTOM)
                .name(request.getName())
                .personalities(request.getPersonalities())
                .file(savedFile)
                .build();

        // 친구를 DB에 저장
        friendRepository.save(friend);

        // 성공 여부 return
        return ResponseEntity.ok("success");
    }

}