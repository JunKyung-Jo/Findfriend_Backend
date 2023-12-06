package com.investment.findfriend.domain.user.presentation;

import com.investment.findfriend.domain.user.presentation.dto.request.UpdateUserInfoRequest;
import com.investment.findfriend.domain.user.presentation.dto.response.UserResponse;
import com.investment.findfriend.domain.user.service.GetUserInfoService;
import com.investment.findfriend.domain.user.service.UpdateUserInfoService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final GetUserInfoService getUserInfoService;
    private final UpdateUserInfoService updateUserInfoService;

    @GetMapping("/get")
    public ResponseEntity<UserResponse> getUserInfo(HttpServletRequest httpServletRequest) {
        return getUserInfoService.execute(httpServletRequest);
    }

    @PutMapping("/update")
    public ResponseEntity<String> updateUserInfo(@RequestBody UpdateUserInfoRequest request, HttpServletRequest httpServletRequest) {
        return updateUserInfoService.execute(request, httpServletRequest);
    }

}
