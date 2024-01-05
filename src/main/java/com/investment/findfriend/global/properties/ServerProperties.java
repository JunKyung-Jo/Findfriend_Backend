package com.investment.findfriend.global.properties;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@ConfigurationProperties(prefix = "server")
@AllArgsConstructor
public class ServerProperties {
    private String path;
    private String url;
}
