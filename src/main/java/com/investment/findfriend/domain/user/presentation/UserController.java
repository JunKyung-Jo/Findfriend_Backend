package com.investment.findfriend.domain.user.presentation;

import com.investment.findfriend.domain.user.presentation.dto.request.UpdateUserInfoRequest;
import com.investment.findfriend.domain.user.presentation.dto.response.UserResponse;
import com.investment.findfriend.domain.user.service.GetUserInfoService;
import com.investment.findfriend.domain.user.service.UpdateUserInfoService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final GetUserInfoService getUserInfoService;
    private final UpdateUserInfoService updateUserInfoService;

    @GetMapping("/get") // 유저 정보 가져오기
    public ResponseEntity<UserResponse> getUserInfo(HttpServletRequest httpServletRequest) {
        return getUserInfoService.execute(httpServletRequest);
    }

    @PutMapping(value = "/update", consumes = MediaType.MULTIPART_FORM_DATA_VALUE) // 유저 정보 업데이트
    public ResponseEntity<String> updateUserInfo(@RequestPart("data") UpdateUserInfoRequest request, @RequestPart("file") MultipartFile file, HttpServletRequest httpServletRequest) {
        return updateUserInfoService.execute(request, file, httpServletRequest);
    }

}
