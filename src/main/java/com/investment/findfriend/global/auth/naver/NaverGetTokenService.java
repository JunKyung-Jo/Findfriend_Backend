package com.investment.findfriend.global.auth.naver;

import com.investment.findfriend.global.auth.dto.request.NaverTokenRequest;
import com.investment.findfriend.global.auth.dto.response.naver.NaverTokenResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(
        name = "NaverGetTokenService",
        url = "https://nid.naver.com/oauth2.0/authorize"
)
public interface NaverGetTokenService {
    @GetMapping
    NaverTokenResponse execute(NaverTokenRequest request);
}
