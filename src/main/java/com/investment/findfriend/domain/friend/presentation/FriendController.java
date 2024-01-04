package com.investment.findfriend.domain.friend.presentation;

import com.investment.findfriend.domain.friend.presentation.dto.request.PostFriendRequest;
import com.investment.findfriend.domain.friend.presentation.dto.request.UpdateAuthorityRequest;
import com.investment.findfriend.domain.friend.presentation.dto.response.FriendResponse;
import com.investment.findfriend.domain.friend.service.*;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/friend")
public class FriendController {

    private final PostFriendService postFriendService;
    private final GetFriendListService getFriendListService;
    private final UpdateAuthorityService updateAuthorityService;
    private final GetFreeFriendListService getFreeFriendListService;


    @PostMapping("/meet")
    public ResponseEntity<String> meetFriend(@RequestPart("data") PostFriendRequest request, @RequestPart MultipartFile file, HttpServletRequest httpServletRequest) {
        return postFriendService.execute(request, file, httpServletRequest);
    }

    @GetMapping("/list")
    public ResponseEntity<List<FriendResponse>> getFriendList(HttpServletRequest httpServletRequest) {
        return getFriendListService.execute(httpServletRequest);
    }

    @PutMapping("/authority")
    public ResponseEntity<String> setAuthority(@RequestBody UpdateAuthorityRequest request, HttpServletRequest httpServletRequest) {
        return updateAuthorityService.execute(request, httpServletRequest);
    }

    @GetMapping
    public ResponseEntity<List<FriendResponse>> getFreeFriendList() {
        return getFreeFriendListService.execute();
    }

}
