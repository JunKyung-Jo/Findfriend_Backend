package com.investment.findfriend.global.jwt.properties;

import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import javax.crypto.SecretKey;

@Getter
@ConfigurationProperties(prefix = "auth.jwt")
public class JwtProperties {
    private final String header;
    private final SecretKey secret;
    private final Long accessExpiration;
    private final Long refreshExpiration;
    private final String prefix;

    public JwtProperties(String header, String secret, Long accessExpiration, Long refreshExpiration, String prefix) {

        SecretKey secretKey = Keys.hmacShaKeyFor(Decoders.BASE64.decode(secret));

        this.header = header;
        this.secret = secretKey;
        this.accessExpiration = accessExpiration;
        this.refreshExpiration = refreshExpiration;
        this.prefix = prefix;
    }
}
