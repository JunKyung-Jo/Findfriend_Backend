package com.investment.findfriend.global.feign.naver;

import com.investment.findfriend.global.feign.dto.response.naver.NaverTokenResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(
        name = "NaverGetTokenClient",
        url = "https://nid.naver.com/oauth2.0/token"
)
public interface NaverGetTokenClient {
    @PostMapping
    NaverTokenResponse execute(@RequestParam("code") String code,
                               @RequestParam("client_id") String clientId,
                               @RequestParam("client_secret") String clientSecret,
                               @RequestParam("grant_type") String grantType,
                               @RequestParam("state") String state);
}
