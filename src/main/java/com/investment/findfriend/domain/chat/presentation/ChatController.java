package com.investment.findfriend.domain.chat.presentation;

import com.investment.findfriend.domain.chat.presentation.dto.request.ChatRequest;
import com.investment.findfriend.domain.chat.presentation.dto.response.ChatResponse;
import com.investment.findfriend.domain.chat.presentation.dto.response.GenerateChatResponse;
import com.investment.findfriend.domain.chat.service.FreeChatService;
import com.investment.findfriend.domain.chat.service.GetListChatService;
import com.investment.findfriend.domain.chat.service.PostChatService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/chat")
public class ChatController {

    private final PostChatService postChatService;
    private final GetListChatService getListChatService;
    private final FreeChatService freeChatService;

    @PostMapping
    public ResponseEntity<GenerateChatResponse> chat(@RequestBody ChatRequest request, HttpServletRequest httpServletRequest) {
        return postChatService.execute(request, httpServletRequest);
    }

    @GetMapping
    public ResponseEntity<List<ChatResponse>> getListChat(@RequestParam Long id, HttpServletRequest httpServletRequest) {
        return getListChatService.execute(id, httpServletRequest);
    }

    @GetMapping("/free")
    public ResponseEntity<GenerateChatResponse> freeChat(@RequestBody ChatRequest request) {
        return freeChatService.execute(request);
    }
}
