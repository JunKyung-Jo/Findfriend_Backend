package com.investment.findfriend.global.feign.dto.request;

import lombok.Builder;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@ConfigurationProperties(prefix = "oauth.naver")
public class NaverTokenRequest {
    private final String response_type = "authorization_code";
    private String client_id;
    private String client_secret;
    private final String code;
    private final String state = "test";
    @Builder
    public NaverTokenRequest(String code) {
        this.code = code;
    }
}
