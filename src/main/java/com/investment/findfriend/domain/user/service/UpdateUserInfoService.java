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
        // 유저 존재 여부 확인
        User user = userRepository.findByEmail(jwtUtil.extractEmail(httpServletRequest)).orElseThrow(
                () -> UserNotFoundException.EXCEPTION
        );

        // 수정할 내용들 바꾸기 ( 프로필 이미지 제외 )
        user.update(request);

        // 파일이 있다면 ?
        if (!file.isEmpty()) {
            // 현재 이미지를 가져오기
            File userFile = user.getFile();

            // 프로필 이미지가 없다면?
            if (userFile == null) {
                user.setFile(File.builder().build()); // 새 객체를 생성
            }
            // 파일을 저장하고 유저 파일 객체에 경로 지정
            user.getFile().setPath(fileSaveUtil.save(file));
            // 파일 저장
            fileRepository.save(user.getFile());
        }

        // 업데이트 된 유저 정보 저장
        userRepository.save(user);
        // 성공 여부 return
        return ResponseEntity.ok("success");
    }


}
