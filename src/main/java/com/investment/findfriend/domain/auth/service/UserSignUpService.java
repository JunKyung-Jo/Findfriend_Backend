package com.investment.findfriend.domain.auth.service;

import com.investment.findfriend.domain.auth.domain.Auth;
import com.investment.findfriend.domain.auth.exception.UserNotFoundException;
import com.investment.findfriend.domain.auth.presentation.dto.response.TokenResponse;
import com.investment.findfriend.domain.user.domain.Authority;
import com.investment.findfriend.domain.user.domain.Gender;
import com.investment.findfriend.domain.user.domain.User;
import com.investment.findfriend.domain.user.repository.UserRepository;
import com.investment.findfriend.global.auth.dto.request.GoogleTokenRequest;
import com.investment.findfriend.global.auth.dto.request.NaverTokenRequest;
import com.investment.findfriend.global.auth.dto.response.google.GoogleTokenResponse;
import com.investment.findfriend.global.auth.dto.response.google.GoogleUserInfoResponse;
import com.investment.findfriend.global.auth.dto.response.naver.NaverTokenResponse;
import com.investment.findfriend.global.auth.dto.response.naver.NaverUserInfoResponse;
import com.investment.findfriend.global.auth.google.GoogleGetTokenService;
import com.investment.findfriend.global.auth.google.GoogleGetUserInfoService;
import com.investment.findfriend.global.auth.naver.NaverGetTokenService;
import com.investment.findfriend.global.auth.naver.NaverGetUserInfoService;
import com.investment.findfriend.global.jwt.util.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class UserSignUpService {

    private final UserRepository userRepository;
    private final GoogleGetTokenService googleGetTokenService;
    private final GoogleGetUserInfoService googleGetUserInfoService;
    private final NaverGetTokenService naverGetTokenService;
    private final NaverGetUserInfoService naverGetUserInfoService;
    private final JwtProvider jwtProvider;

    public ResponseEntity<TokenResponse> execute(String code, Auth auth) {
        String email = null;
        if (auth == Auth.GOOGLE) {
            GoogleTokenResponse googleTokenResponse = googleGetTokenService.execute(
                    GoogleTokenRequest.builder()
                            .code(code)
                            .build()
            );
            GoogleUserInfoResponse googleUserInfoResponse = googleGetUserInfoService.execute(
                    googleTokenResponse.getAccess_token()
            );

            if (userRepository.findByEmail(googleUserInfoResponse.getEmail()).isEmpty()) {
                userRepository.save(
                        User.builder()
                                .name(googleUserInfoResponse.getName())
                                .authority(Authority.ROLE_USER)
                                .email(googleUserInfoResponse.getEmail())
                                .build()
                );
            }

            email = googleUserInfoResponse.getEmail();
        } else if (auth == Auth.NAVER) {
            NaverTokenResponse naverTokenResponse = naverGetTokenService.execute(
                    NaverTokenRequest.builder()
                            .code(code)
                            .build()
            );
            NaverUserInfoResponse naverUserInfoResponse = naverGetUserInfoService.execute(
                    naverTokenResponse.getToken_type() + " " + naverTokenResponse.getAccess_token()
            ).getResponse();

            if (userRepository.findByEmail(naverUserInfoResponse.getEmail()).isEmpty()) {
                userRepository.save(
                        User.builder()
                                .name(naverUserInfoResponse.getName())
                                .email(naverUserInfoResponse.getEmail())
                                .gender(Gender.valueOf(naverUserInfoResponse.getGender()))
                                .authority(Authority.ROLE_USER)
                                .birthdate(LocalDate.parse(naverUserInfoResponse.getBirthyear() + "-" + naverUserInfoResponse.getBirthday()))
                                .phone(naverUserInfoResponse.getMobile())
                                .build()
                );
            }
            email = naverUserInfoResponse.getEmail();
        }
        User user = userRepository.findByEmail(email).orElseThrow(() -> UserNotFoundException.EXCEPTION);
        return ResponseEntity.ok(TokenResponse.builder()
                .accessToken(jwtProvider.createAccessToken(user.getEmail(), user.getAuthority()))
                .refreshToken(jwtProvider.createRefreshToken(user.getEmail(), user.getAuthority()))
                .build()
        );
    }
}
