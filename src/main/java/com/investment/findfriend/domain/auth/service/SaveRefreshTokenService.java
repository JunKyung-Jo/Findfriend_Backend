package com.investment.findfriend.domain.auth.service;

import com.investment.findfriend.domain.auth.domain.RefreshToken;
import com.investment.findfriend.domain.auth.presentation.dto.response.TokenResponse;
import com.investment.findfriend.domain.auth.repository.RefreshTokenRepository;
import com.investment.findfriend.domain.user.domain.type.Authority;
import com.investment.findfriend.global.jwt.util.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SaveRefreshTokenService {

    private final RefreshTokenRepository refreshTokenRepository;
    private final JwtProvider jwtProvider;

    public ResponseEntity<TokenResponse> execute(String email, Authority authority) {
        String accessToken = jwtProvider.createAccessToken(email, authority);
        String refreshToken = jwtProvider.createRefreshToken(email, authority);

        Optional<RefreshToken> refresh_token = refreshTokenRepository.findByEmail(email);

        if (refresh_token.isEmpty()) {
            refreshTokenRepository.save(
                    RefreshToken.builder()
                            .email(email)
                            .accessToken(accessToken)
                            .refreshToken(refreshToken)
                            .build()
            );
        }
        else {
            refresh_token.get().setToken(accessToken, refreshToken);
        }
        return ResponseEntity.ok(TokenResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build());
    }
}
