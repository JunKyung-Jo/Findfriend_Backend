package com.investment.findfriend.domain.feed.service;

import com.investment.findfriend.domain.auth.service.FileSaveUtil;
import com.investment.findfriend.domain.feed.domain.Feed;
import com.investment.findfriend.domain.feed.exception.FileNotFoundException;
import com.investment.findfriend.domain.feed.exception.UnAuthorizedException;
import com.investment.findfriend.domain.feed.presentation.dto.request.PostFeedRequest;
import com.investment.findfriend.domain.feed.repository.FeedRepository;
import com.investment.findfriend.domain.file.domain.File;
import com.investment.findfriend.domain.file.repository.FileRepository;
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
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostFeedService {

    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;
    private final FriendRepository friendRepository;
    private final FeedRepository feedRepository;
    private final FileRepository fileRepository;
    private final FileSaveUtil fileSaveUtil;

    public ResponseEntity<String> execute(PostFeedRequest request, MultipartFile file, HttpServletRequest httpServletRequest) {
        // 사진이 있는지 확인
        if (file.isEmpty()) {
            throw FileNotFoundException.EXCEPTION;
        }
        Optional<User> user = userRepository.findByEmail(jwtUtil.extractEmail(httpServletRequest));
        // 유저 권한이 어드민인지 ? 그리고 가입된 유저인지 확인
        if (user.isEmpty() || user.get().getAuthority() != Authority.ROLE_ADMIN) {
            throw UnAuthorizedException.EXCEPTION;
        }
        // 파일 저장
        String path = fileSaveUtil.save(file);

        // 피드 저장
        feedRepository.save(Feed.builder()
                .content(request.getContent())
                .friend(friendRepository.findById(request.getFriendId()).orElseThrow(
                        () -> FriendNotFoundException.EXCEPTION
                ))
                .file(fileRepository.save(File.builder()
                        .path(path)
                        .build()))
                .tags(request.getTags())
                .build());
        // 성공 return
        return ResponseEntity.ok("success");
    }
}
