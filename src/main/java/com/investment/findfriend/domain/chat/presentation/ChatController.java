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

    @PostMapping // 채팅하기 ( 대화 내용 저장 )
    public ResponseEntity<GenerateChatResponse> chat(@RequestBody ChatRequest request, HttpServletRequest httpServletRequest) {
        return postChatService.execute(request, httpServletRequest);
    }

    @GetMapping // 채팅 내용 가져오기
    public ResponseEntity<List<ChatResponse>> getListChat(@RequestParam Long id, HttpServletRequest httpServletRequest) {
        return getListChatService.execute(id, httpServletRequest);
    }

    @PostMapping("/free") // 무료로 대화만 받기
    public ResponseEntity<GenerateChatResponse> freeChat(@RequestBody ChatRequest request) {
        return freeChatService.execute(request);
    }
}
