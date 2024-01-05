package com.investment.findfriend.global.properties;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@ConfigurationProperties(prefix = "root")
@AllArgsConstructor
public class ServerProperties {
    private String path;
}
