package com.investment.findfriend.global.jwt.filter;

import com.investment.findfriend.domain.auth.repository.RefreshTokenRepository;
import com.investment.findfriend.global.jwt.exception.LoggedOutAccessTokenException;
import com.investment.findfriend.global.jwt.exception.TokenNotFoundException;
import com.investment.findfriend.global.jwt.util.JwtUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;
    private final RefreshTokenRepository refreshTokenRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = jwtUtil.resolveToken(request);

        if (token == null) {
            throw TokenNotFoundException.EXCEPTION;
        } else {
            if (!refreshTokenRepository.existsByAccessToken(token)) {
                throw LoggedOutAccessTokenException.EXCEPTION;
            }

            Authentication authentication = jwtUtil.getAuthentication(token);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        filterChain.doFilter(request, response);
    }
}
