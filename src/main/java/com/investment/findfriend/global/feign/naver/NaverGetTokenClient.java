package com.investment.findfriend.global.feign.naver;

import com.investment.findfriend.global.feign.dto.request.naver.NaverTokenRequest;
import com.investment.findfriend.global.feign.dto.response.naver.NaverTokenResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(
        name = "NaverGetTokenClient",
        url = "https://nid.naver.com/oauth2.0/authorize"
)
public interface NaverGetTokenClient {
    @GetMapping
    NaverTokenResponse execute(NaverTokenRequest request);
}
