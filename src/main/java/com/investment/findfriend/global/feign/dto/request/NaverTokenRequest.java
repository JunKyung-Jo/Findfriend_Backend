package com.investment.findfriend.global.feign.dto.request;

import lombok.Builder;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Builder
public class NaverTokenRequest {
    private final String response_type = "authorization_code";
    private final String client_id;
    private final String client_secret;
    private final String code;
    private final String state = "test";
}
