package com.investment.findfriend.domain.auth.service;

import com.investment.findfriend.domain.auth.domain.Auth;
import com.investment.findfriend.domain.auth.presentation.dto.response.TokenResponse;
import com.investment.findfriend.domain.file.domain.File;
import com.investment.findfriend.domain.file.repository.FileRepository;
import com.investment.findfriend.domain.user.domain.User;
import com.investment.findfriend.domain.user.domain.type.Authority;
import com.investment.findfriend.domain.user.domain.type.Gender;
import com.investment.findfriend.domain.user.repository.UserRepository;
import com.investment.findfriend.global.feign.dto.request.google.GoogleTokenRequest;
import com.investment.findfriend.global.feign.dto.response.google.GoogleTokenResponse;
import com.investment.findfriend.global.feign.dto.response.google.GoogleUserInfoResponse;
import com.investment.findfriend.global.feign.dto.response.naver.NaverTokenResponse;
import com.investment.findfriend.global.feign.dto.response.naver.NaverUserInfoResponse;
import com.investment.findfriend.global.feign.google.GoogleGetTokenClient;
import com.investment.findfriend.global.feign.google.GoogleGetUserInfoClient;
import com.investment.findfriend.global.feign.naver.NaverGetTokenClient;
import com.investment.findfriend.global.feign.naver.NaverGetUserInfoClient;
import com.investment.findfriend.global.feign.properties.GoogleAuthProperties;
import com.investment.findfriend.global.feign.properties.NaverAuthProperties;
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
    private final GoogleAuthProperties googleAuthProperties;
    private final NaverAuthProperties naverAuthProperties;
    private final SaveRefreshTokenService saveRefreshTokenService;
    private final FileRepository fileRepository;
    public ResponseEntity<TokenResponse> execute(String code, Auth auth) {
        // auth enum(열거형)을 통해서 구글 네이버 판단
        if (auth == Auth.GOOGLE) {
            GoogleTokenResponse googleTokenResponse = googleGetTokenClient.execute(
                    GoogleTokenRequest.builder()
                            .code(code)
                            .client_id(googleAuthProperties.getClient_id())
                            .client_secret(googleAuthProperties.getClient_secret())
                            .redirect_uri(googleAuthProperties.getRedirect_uri())
                            .build()
            );
            GoogleUserInfoResponse googleUserInfoResponse = googleGetUserInfoClient.execute(
                    googleTokenResponse.getAccess_token()
            );

            // 가져온 정보를 바탕으로 이미 가입이 되어있는지 확인
            if (userRepository.findByEmail(googleUserInfoResponse.getEmail()).isEmpty()) { // 가입이 안되어있다면 새 데이터 저장
                userRepository.save(
                        User.builder()
                        .name(googleUserInfoResponse.getName())
                        .authority(Authority.ROLE_USER)
                        .email(googleUserInfoResponse.getEmail())
                        .statusMessage("상태 메시지")
                                .file(fileRepository.save(File.builder().build()))
                        .build());
            }

            // 토큰 발급 후 return
            return saveRefreshTokenService.execute(googleUserInfoResponse.getEmail());
        } else if (auth == Auth.NAVER) {
            NaverTokenResponse naverTokenResponse = naverGetTokenClient.execute(
                    code,
                    naverAuthProperties.getClient_id(),
                    naverAuthProperties.getClient_secret(),
                    "authorization_code",
                    "test"
            );
            NaverUserInfoResponse naverUserInfoResponse = naverGetUserInfoClient.execute(
                    naverTokenResponse.getToken_type() + " " + naverTokenResponse.getAccess_token()
            ).getResponse();
            // 가져온 정보를 바탕으로 이미 가입이 되어있는지 확인
            if (userRepository.findByEmail(naverUserInfoResponse.getEmail()).isEmpty()) { // 가입이 안되어있다면 새 데이터 저장
                userRepository.save(
                        User.builder()
                        .name(naverUserInfoResponse.getName())
                        .email(naverUserInfoResponse.getEmail())
                        .gender(naverUserInfoResponse.getGender().equals("M") ? Gender.MALE : Gender.FEMALE)
                        .authority(Authority.ROLE_USER)
                        .birthdate(LocalDate.parse(naverUserInfoResponse.getBirthyear() + "-" + naverUserInfoResponse.getBirthday()))
                        .phone(naverUserInfoResponse.getMobile())
                        .statusMessage("상태 메시지")
                                .file(fileRepository.save(File.builder().build()))
                        .build());
            }
            // 토큰 발급 후 return
            return saveRefreshTokenService.execute(naverUserInfoResponse.getEmail());
        }
        // 열거형이 올바르지 않다면 토큰을 발급하지 않음
        return null;
    }
}
