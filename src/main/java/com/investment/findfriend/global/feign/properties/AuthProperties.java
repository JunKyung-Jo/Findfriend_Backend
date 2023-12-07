package com.investment.findfriend.global.feign.properties;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@ConfigurationProperties(prefix = "oauth.google")
@AllArgsConstructor
public class AuthProperties {
    private String client_id;
    private String client_secret;
    private String redirect_uri;
}
