package com.investment.findfriend.global.auth.google;


import com.investment.findfriend.global.auth.dto.response.google.GoogleUserInfoResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(
        name = "GetUserInfoService",
        url = "https://www.googleapis.com/userinfo/v2/me"
)
public interface GoogleGetUserInfoService {
    @GetMapping
    GoogleUserInfoResponse execute(@RequestParam String access_token);
}
