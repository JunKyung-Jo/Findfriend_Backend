package com.investment.findfriend.global.jwt.util;

import com.investment.findfriend.global.jwt.properties.JwtProperties;
import com.investment.findfriend.global.security.auth.AuthDetails;
import com.investment.findfriend.global.security.auth.AuthDetailsService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class JwtUtil {

    private final JwtProperties jwtProperties;
    private final AuthDetailsService authDetailsService;

    public String resolveToken(HttpServletRequest request) {
        String bearer = request.getHeader("Authorization");

        if (bearer == null || !bearer.startsWith("Bearer ")) {
            return null;
        }
        return bearer.split(" ")[1].trim();
    }

    public Authentication getAuthentication(String token) {
        AuthDetails authDetails = (AuthDetails) authDetailsService.loadUserByUsername(extractEmailByToken(token));
        return new UsernamePasswordAuthenticationToken(authDetails, token, authDetails.getAuthorities());
    }

    private String extractEmailByToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(jwtProperties.getSecret())
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }
    public String extractEmail(HttpServletRequest request) {
        return Jwts.parserBuilder()
                .setSigningKey(jwtProperties.getSecret())
                .build()
                .parseClaimsJws(request.getHeader("Authorization").split(" ")[1].trim())
                .getBody()
                .getSubject();
    }

    public Claims getClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(jwtProperties.getSecret())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}
