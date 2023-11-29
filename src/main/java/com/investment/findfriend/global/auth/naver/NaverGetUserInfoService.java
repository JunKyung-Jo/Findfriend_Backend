package com.investment.findfriend.global.auth.naver;

import com.investment.findfriend.global.auth.dto.response.naver.NaverResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(
        name = "NaverGetUserInfoService",
        url = "https://openapi.naver.com/v1/nid/me"
)
public interface NaverGetUserInfoService {
    @GetMapping
    NaverResponse execute(@RequestParam String Authorization);
}
