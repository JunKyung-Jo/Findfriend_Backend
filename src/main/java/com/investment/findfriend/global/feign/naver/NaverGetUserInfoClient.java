package com.investment.findfriend.global.feign.naver;

import com.investment.findfriend.global.feign.dto.response.naver.NaverResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(
        name = "NaverGetUserInfoClient",
        url = "https://openapi.naver.com/v1/nid/me"
)
public interface NaverGetUserInfoClient {
    @GetMapping
    NaverResponse execute(@RequestHeader String Authorization);
}
