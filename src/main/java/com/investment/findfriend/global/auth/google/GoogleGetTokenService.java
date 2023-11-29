package com.investment.findfriend.global.auth.google;

import com.investment.findfriend.global.auth.dto.request.GoogleTokenRequest;
import com.investment.findfriend.global.auth.dto.response.google.GoogleTokenResponse;
import feign.Headers;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(
        name = "GoogleGetTokenService",
        url = "https://oauth2.googleapis.com/token"
)
public interface GoogleGetTokenService {
    @PostMapping
    @Headers("Content-Type: application/x-www-form-urlencoded")
    GoogleTokenResponse execute(@RequestBody GoogleTokenRequest request);
}
