package com.investment.findfriend.global.auth.dto.request;

import lombok.Builder;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@ConfigurationProperties("spring.security.oauth2.client.registration.google")
public class GoogleTokenRequest {
    private final String code;
    private String client_id;
    private String client_secret;
    private String redirect_uri;
    private final String grant_type = "authorization_code";
    @Builder
    public GoogleTokenRequest(String code) {
        this.code = code;
    }
}
