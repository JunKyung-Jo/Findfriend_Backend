package com.investment.findfriend.domain.friend.presentation;

import com.investment.findfriend.domain.friend.presentation.dto.request.PostFriendRequest;
import com.investment.findfriend.domain.friend.service.PostFriendService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/friend")
public class FriendController {

    private final PostFriendService postFriendService;

    @PostMapping("/meet")
    public ResponseEntity<String> meetFriend(@RequestBody PostFriendRequest request, HttpServletRequest httpServletRequest) {
        return postFriendService.execute(request, httpServletRequest);
    }

}
