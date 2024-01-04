package com.investment.findfriend.domain.feed.service;

import com.investment.findfriend.domain.feed.domain.Feed;
import com.investment.findfriend.domain.feed.exception.FeedNotFoundException;
import com.investment.findfriend.domain.feed.presentation.dto.response.FeedResponse;
import com.investment.findfriend.domain.feed.repository.FeedRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetFeedService {
    private final FeedRepository feedRepository;


    public ResponseEntity<FeedResponse> execute(Long feedId) {
        Feed feed = feedRepository.findById(feedId).orElseThrow(
                () -> FeedNotFoundException.EXCEPTION
        );
        return ResponseEntity.ok(FeedResponse.builder()
                .content(feed.getContent())
                .tags(feed.getTags())
                .build()
        );
    }
}
