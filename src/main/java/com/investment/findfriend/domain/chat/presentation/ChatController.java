package com.investment.findfriend.domain.chat.presentation;

import com.investment.findfriend.domain.chat.presentation.dto.request.ChatRequest;
import com.investment.findfriend.domain.chat.presentation.dto.response.ChatResponse;
import com.investment.findfriend.domain.chat.service.PostChatService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/chat")
public class ChatController {

    private final PostChatService postChatService;

    @PostMapping
    public ResponseEntity<ChatResponse> chat(@RequestBody ChatRequest request, HttpServletRequest httpServletRequest) {
        return postChatService.execute(request, httpServletRequest);
    }
}
