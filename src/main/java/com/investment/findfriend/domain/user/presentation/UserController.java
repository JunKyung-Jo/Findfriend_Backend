package com.investment.findfriend.domain.user.presentation;

import com.investment.findfriend.domain.user.presentation.dto.response.UserResponse;
import com.investment.findfriend.domain.user.service.GetUserInfoService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final GetUserInfoService getUserInfoService;

    @GetMapping("/get")
    public ResponseEntity<UserResponse> getUserInfo(HttpServletRequest request) {
        return getUserInfoService.execute(request);
    }

}
