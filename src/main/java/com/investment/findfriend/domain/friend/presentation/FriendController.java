package com.investment.findfriend.domain.friend.presentation;

import com.investment.findfriend.domain.friend.presentation.dto.request.PostFriendRequest;
import com.investment.findfriend.domain.friend.presentation.dto.request.UpdateAuthorityRequest;
import com.investment.findfriend.domain.friend.presentation.dto.response.FriendResponse;
import com.investment.findfriend.domain.friend.service.GetFriendListService;
import com.investment.findfriend.domain.friend.service.PostFriendService;
import com.investment.findfriend.domain.friend.service.UpdateAuthorityService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/friend")
public class FriendController {

    private final PostFriendService postFriendService;
    private final GetFriendListService getFriendListService;
    private final UpdateAuthorityService updateAuthorityService;

    @PostMapping("/meet")
    public ResponseEntity<String> meetFriend(@RequestBody PostFriendRequest request, HttpServletRequest httpServletRequest) {
        return postFriendService.execute(request, httpServletRequest);
    }

    @GetMapping("/list")
    public ResponseEntity<List<FriendResponse>> getFriendList(HttpServletRequest httpServletRequest) {
        return getFriendListService.execute(httpServletRequest);
    }

    @PutMapping("/authority")
    public ResponseEntity<String> setAuthority(@RequestBody UpdateAuthorityRequest request, HttpServletRequest httpServletRequest) {
        return updateAuthorityService.execute(request, httpServletRequest);
    }

}
