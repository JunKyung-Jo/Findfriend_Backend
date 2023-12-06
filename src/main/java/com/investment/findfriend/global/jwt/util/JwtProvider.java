package com.investment.findfriend.global.jwt.util;

import com.investment.findfriend.domain.user.domain.type.Authority;
import com.investment.findfriend.global.jwt.properties.JwtProperties;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@RequiredArgsConstructor
public class JwtProvider {

    private final JwtProperties jwtProperties;

    public String createAccessToken(String email, Authority authority) {
        return createToken(email, authority, jwtProperties.getAccessExp());
    }

    public String createRefreshToken(String email, Authority authority) {
        return createToken(email, authority, jwtProperties.getRefreshExp());
    }

    private String createToken(String email, Authority authority, long expirationTime) {
        return Jwts.builder()
                .setSubject(email)
                .claim("AUTHORIZATION_KEY", authority)
                .signWith(jwtProperties.getSecret(), SignatureAlgorithm.HS256)
                .setExpiration(new Date(expirationTime))
                .compact();

    }
}
