package com.investment.findfriend.domain.feed.presentation;

import com.investment.findfriend.domain.feed.presentation.dto.request.PostFeedRequest;
import com.investment.findfriend.domain.feed.service.PostFeedService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping("/feed")
public class FeedController {

    private final PostFeedService postPostService;

    @PostMapping
    public ResponseEntity<String> postFeed(@RequestPart("data") PostFeedRequest request, @RequestPart("file") MultipartFile file,  HttpServletRequest httpServletRequest) {
        return postPostService.execute(request, file, httpServletRequest);
    }

}
