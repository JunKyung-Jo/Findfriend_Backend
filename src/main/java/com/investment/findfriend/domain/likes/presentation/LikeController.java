package com.investment.findfriend.domain.likes.presentation;

import com.investment.findfriend.domain.likes.service.CheckLikedService;
import com.investment.findfriend.domain.likes.service.DeleteLikeService;
import com.investment.findfriend.domain.likes.service.FeedLikeService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/like")
public class LikeController {

    private final FeedLikeService feedLikeService;
    private final CheckLikedService checkLikedService;
    private final DeleteLikeService deleteLikeService;

    @PostMapping
    public ResponseEntity<String> likeFeed(@RequestParam Long feedId, HttpServletRequest httpServletRequest) {
        return feedLikeService.execute(feedId, httpServletRequest);
    }

    @GetMapping
    public ResponseEntity<Boolean> isLiked(@RequestParam Long feedId, HttpServletRequest httpServletRequest) {
        return checkLikedService.execute(feedId, httpServletRequest);
    }

    @DeleteMapping
    public ResponseEntity<String> deleteLike(@RequestParam Long feedId, HttpServletRequest httpServletRequest) {
        return deleteLikeService.execute(feedId, httpServletRequest);
    }
}
