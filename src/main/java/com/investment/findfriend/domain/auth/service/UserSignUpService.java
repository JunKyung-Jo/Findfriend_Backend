package com.investment.findfriend.domain.auth.service;

import com.investment.findfriend.domain.auth.domain.Auth;
import com.investment.findfriend.domain.auth.exception.UserNotFoundException;
import com.investment.findfriend.domain.auth.presentation.dto.response.TokenResponse;
import com.investment.findfriend.domain.user.domain.type.Authority;
import com.investment.findfriend.domain.user.domain.type.Gender;
import com.investment.findfriend.domain.user.domain.User;
import com.investment.findfriend.domain.user.repository.UserRepository;
import com.investment.findfriend.global.feign.dto.request.GoogleTokenRequest;
import com.investment.findfriend.global.feign.dto.request.NaverTokenRequest;
import com.investment.findfriend.global.feign.dto.response.google.GoogleTokenResponse;
import com.investment.findfriend.global.feign.dto.response.google.GoogleUserInfoResponse;
import com.investment.findfriend.global.feign.dto.response.naver.NaverTokenResponse;
import com.investment.findfriend.global.feign.dto.response.naver.NaverUserInfoResponse;
import com.investment.findfriend.global.feign.google.GoogleGetTokenClient;
import com.investment.findfriend.global.feign.google.GoogleGetUserInfoClient;
import com.investment.findfriend.global.feign.naver.NaverGetTokenClient;
import com.investment.findfriend.global.feign.naver.NaverGetUserInfoClient;
import com.investment.findfriend.global.feign.properties.AuthProperties;
import com.investment.findfriend.global.jwt.util.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class UserSignUpService {

    private final UserRepository userRepository;
    private final GoogleGetTokenClient googleGetTokenClient;
    private final GoogleGetUserInfoClient googleGetUserInfoClient;
    private final NaverGetTokenClient naverGetTokenClient;
    private final NaverGetUserInfoClient naverGetUserInfoClient;
    private final JwtProvider jwtProvider;
    private final AuthProperties authProperties;

    public ResponseEntity<TokenResponse> execute(String code, Auth auth) {
        String email = null;
        if (auth == Auth.GOOGLE) {
            GoogleTokenResponse googleTokenResponse = googleGetTokenClient.execute(
                    GoogleTokenRequest.builder()
                            .code(code)
                            .client_id(authProperties.getClient_id())
                            .client_secret(authProperties.getClient_secret())
                            .redirect_uri(authProperties.getRedirect_uri())
                            .build()
            );
            GoogleUserInfoResponse googleUserInfoResponse = googleGetUserInfoClient.execute(
                    googleTokenResponse.getAccess_token()
            );

            if (userRepository.findByEmail(googleUserInfoResponse.getEmail()).isEmpty()) {
                userRepository.save(
                        User.builder()
                                .name(googleUserInfoResponse.getName())
                                .authority(Authority.ROLE_USER)
                                .email(googleUserInfoResponse.getEmail())
                                .statusMessage("상태 메시지")
                                .build()
                );
            }

            email = googleUserInfoResponse.getEmail();
        } else if (auth == Auth.NAVER) {
            NaverTokenResponse naverTokenResponse = naverGetTokenClient.execute(
                    NaverTokenRequest.builder()
                            .code(code)
                            .build()
            );
            NaverUserInfoResponse naverUserInfoResponse = naverGetUserInfoClient.execute(
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
                                .statusMessage("상태 메시지")
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
