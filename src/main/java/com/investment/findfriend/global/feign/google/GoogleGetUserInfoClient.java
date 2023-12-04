package com.investment.findfriend.global.feign.google;


import com.investment.findfriend.global.feign.dto.response.google.GoogleUserInfoResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(
        name = "GoogleGetUserInfoClient",
        url = "https://www.googleapis.com/userinfo/v2/me"
)
public interface GoogleGetUserInfoClient {
    @GetMapping
    GoogleUserInfoResponse execute(@RequestParam String access_token);
}
