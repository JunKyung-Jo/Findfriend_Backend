package com.investment.findfriend.domain.feed.presentation;

import com.investment.findfriend.domain.feed.presentation.dto.request.PostFeedRequest;
import com.investment.findfriend.domain.feed.presentation.dto.response.FeedResponse;
import com.investment.findfriend.domain.feed.service.PostFeedService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/feed")
public class FeedController {

    private final PostFeedService postPostService;

    @PostMapping
    public ResponseEntity<FeedResponse> postFeed(@RequestBody PostFeedRequest request, HttpServletRequest httpServletRequest) {
        return postPostService.execute(request, httpServletRequest);
    }

}
