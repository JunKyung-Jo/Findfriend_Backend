package com.investment.findfriend.global.feign.properties;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@ConfigurationProperties(prefix = "openai")
@AllArgsConstructor
public class ChatGPTProperties {
    private String apiKey;
    private String model;
    private int max_tokens;
    private int temperature;
}
