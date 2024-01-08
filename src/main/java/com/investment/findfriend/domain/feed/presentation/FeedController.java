package com.investment.findfriend.domain.feed.presentation;

import com.investment.findfriend.domain.feed.presentation.dto.request.PostFeedRequest;
import com.investment.findfriend.domain.feed.presentation.dto.response.FeedListResponse;
import com.investment.findfriend.domain.feed.presentation.dto.response.FeedResponse;
import com.investment.findfriend.domain.feed.service.GetFeedListService;
import com.investment.findfriend.domain.feed.service.GetFeedService;
import com.investment.findfriend.domain.feed.service.PostFeedService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/feed")
public class FeedController {

    private final PostFeedService postPostService;
    private final GetFeedListService getFeedListService;
    private final GetFeedService getFeedService;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE) // 피드 생성하기
    public ResponseEntity<String> postFeed(@RequestPart("data") PostFeedRequest request, @RequestPart("file") MultipartFile file,  HttpServletRequest httpServletRequest) {
        return postPostService.execute(request, file, httpServletRequest);
    }

    @GetMapping("/list") // 피드 리스트 가져오기
    public ResponseEntity<List<FeedListResponse>> getAllFeeds(@RequestParam Long friendId) {
        return getFeedListService.execute(friendId);
    }

    @GetMapping // 피드 상세보기
    public ResponseEntity<FeedResponse> getFeed(@RequestParam Long feedId) {
        return getFeedService.execute(feedId);
    }

}
