package com.investment.findfriend.global.feign.dto.request.google;

import lombok.*;

@Getter
@Builder
public class GoogleTokenRequest {
    private String code;
    private String client_id;
    private String client_secret;
    private String redirect_uri;
    private final String grant_type = "authorization_code";
}
