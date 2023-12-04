package com.investment.findfriend.global.feign.dto.request;

import lombok.Builder;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@ConfigurationProperties("oauth.google")
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
