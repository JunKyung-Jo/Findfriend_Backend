package com.investment.findfriend.global.feign.dto.response.naver;

import lombok.Getter;

@Getter
public class NaverTokenResponse {
    private String access_token;
    private String token_type;
}
