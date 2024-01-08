package com.investment.findfriend.domain.likes.presentation;

import com.investment.findfriend.domain.likes.presentation.dto.response.LikesResponse;
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

    @PostMapping // 좋아요 누르기
    public ResponseEntity<String> likeFeed(@RequestParam Long feedId, HttpServletRequest httpServletRequest) {
        return feedLikeService.execute(feedId, httpServletRequest);
    }

    @GetMapping // 좋아요를 눌렀는지 확인
    public ResponseEntity<LikesResponse> isLiked(@RequestParam Long feedId, HttpServletRequest httpServletRequest) {
        return checkLikedService.execute(feedId, httpServletRequest);
    }

    @DeleteMapping // 좋아요 삭제하기
    public ResponseEntity<String> deleteLike(@RequestParam Long feedId, HttpServletRequest httpServletRequest) {
        return deleteLikeService.execute(feedId, httpServletRequest);
    }
}
