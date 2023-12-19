package com.investment.findfriend.domain.auth.service;

import com.investment.findfriend.domain.auth.domain.RefreshToken;
import com.investment.findfriend.domain.auth.exception.BadRequestException;
import com.investment.findfriend.domain.auth.exception.RefreshTokenNotFoundException;
import com.investment.findfriend.domain.auth.presentation.dto.response.TokenResponse;
import com.investment.findfriend.domain.auth.repository.RefreshTokenRepository;
import com.investment.findfriend.global.jwt.util.JwtProvider;
import com.investment.findfriend.global.jwt.util.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RefreshTokenService {

    private final JwtUtil jwtUtil;
    private final JwtProvider jwtProvider;
    private final RefreshTokenRepository refreshTokenRepository;

    @Transactional
    public ResponseEntity<TokenResponse> execute(HttpServletRequest request) {
        try {
            String email = jwtUtil.extractEmailByToken(
                    request.getHeader("Authorization-refresh").split(" ")[1].trim()
            );

            RefreshToken refreshToken = refreshTokenRepository.findByEmail(email).orElseThrow(
                    () -> RefreshTokenNotFoundException.EXCEPTION
            );
            String newAccessToken = jwtProvider.createAccessToken(email);
            String newRefreshToken = jwtProvider.createRefreshToken(email);
            refreshToken.setToken(newAccessToken, newRefreshToken);
            refreshTokenRepository.save(refreshToken);
            return ResponseEntity.ok(TokenResponse.builder()
                    .accessToken(newAccessToken)
                    .refreshToken(newRefreshToken)
                    .build());
        } catch (NullPointerException e) {
            throw BadRequestException.EXCEPTION;
        }
    }
}
