package com.investment.findfriend.global.feign.gpt;

import com.investment.findfriend.global.feign.dto.request.gpt.ChatGPTRequest;
import com.investment.findfriend.global.feign.dto.response.gpt.ChatGPTResponse;
import feign.Headers;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(
        name = "ChatGPTClient",
        url = "https://api.openai.com/v1/completions"
)
public interface ChatGPTClient {
    @PostMapping
    @Headers({
            "Content-Type: application/json",
            "Authorization: Bearer {apiKey}",
    })
    ChatGPTResponse getChatGPTResponse(@RequestBody ChatGPTRequest chatGPTRequest, String apiKey);
}
