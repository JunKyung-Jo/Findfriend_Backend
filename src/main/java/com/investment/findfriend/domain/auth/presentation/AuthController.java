package com.investment.findfriend.domain.auth.presentation;

import com.investment.findfriend.domain.auth.domain.Auth;
import com.investment.findfriend.domain.auth.presentation.dto.response.TokenResponse;
import com.investment.findfriend.domain.auth.service.RefreshTokenService;
import com.investment.findfriend.domain.auth.service.UserSignUpService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final UserSignUpService userSignUpService;
    private final RefreshTokenService refreshTokenService;

    @PostMapping("/signup")
    public ResponseEntity<TokenResponse> signUp(@RequestParam("code") String code, @RequestParam("auth")Auth auth) {
        return userSignUpService.execute(code, auth);
    }

    @PutMapping("/refresh")
    public ResponseEntity<TokenResponse> refresh(HttpServletRequest request) {
        return refreshTokenService.execute(request);
    }

}
