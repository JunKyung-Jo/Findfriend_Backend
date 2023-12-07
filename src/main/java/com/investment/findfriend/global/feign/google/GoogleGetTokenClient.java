package com.investment.findfriend.global.feign.google;

import com.investment.findfriend.global.feign.dto.request.google.GoogleTokenRequest;
import com.investment.findfriend.global.feign.dto.response.google.GoogleTokenResponse;
import feign.Headers;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(
        name = "GoogleGetTokenClient",
        url = "https://oauth2.googleapis.com/token"
)
public interface GoogleGetTokenClient {
    @PostMapping
    @Headers("Content-Type: application/x-www-form-urlencoded")
    GoogleTokenResponse execute(@RequestBody GoogleTokenRequest request);
}
