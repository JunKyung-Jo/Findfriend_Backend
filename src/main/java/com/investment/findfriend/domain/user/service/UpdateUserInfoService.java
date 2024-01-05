package com.investment.findfriend.domain.user.service;

import com.investment.findfriend.domain.auth.exception.UserNotFoundException;
import com.investment.findfriend.domain.auth.service.FileSaveUtil;
import com.investment.findfriend.domain.file.domain.File;
import com.investment.findfriend.domain.file.repository.FileRepository;
import com.investment.findfriend.domain.user.domain.User;
import com.investment.findfriend.domain.user.presentation.dto.request.UpdateUserInfoRequest;
import com.investment.findfriend.domain.user.repository.UserRepository;
import com.investment.findfriend.global.jwt.util.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class UpdateUserInfoService {

    private final UserRepository userRepository;
    private final FileRepository fileRepository;
    private final JwtUtil jwtUtil;
    private final FileSaveUtil fileSaveUtil;

    public ResponseEntity<String> execute(UpdateUserInfoRequest request, MultipartFile file, HttpServletRequest httpServletRequest) {
        User user = userRepository.findByEmail(jwtUtil.extractEmail(httpServletRequest)).orElseThrow(
                () -> UserNotFoundException.EXCEPTION
        );
        user.update(request);
        if (!file.isEmpty()) {
            File userFile = user.getFile();
            userFile.setPath(fileSaveUtil.save(file));
            fileRepository.save(userFile);
            userRepository.save(user);
        }
        return ResponseEntity.ok("success");
    }
}
