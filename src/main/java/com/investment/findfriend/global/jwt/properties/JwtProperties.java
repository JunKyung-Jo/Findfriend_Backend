package com.investment.findfriend.global.jwt.properties;

import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import javax.crypto.SecretKey;

@Getter
@ConfigurationProperties(prefix = "jwt")
public class JwtProperties {
    private final SecretKey secret;
    private final Long accessExp;
    private final Long refreshExp;

    public JwtProperties(String secret, Long accessExp, Long refreshExp) {
        this.secret = Keys.hmacShaKeyFor(Decoders.BASE64.decode(secret));
        this.accessExp = accessExp;
        this.refreshExp = refreshExp;
    }
}
