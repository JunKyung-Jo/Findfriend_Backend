package com.investment.findfriend.domain.friend.presentation;

import com.investment.findfriend.domain.friend.presentation.dto.request.PostFriendRequest;
import com.investment.findfriend.domain.friend.presentation.dto.request.UpdateAuthorityRequest;
import com.investment.findfriend.domain.friend.presentation.dto.response.FriendResponse;
import com.investment.findfriend.domain.friend.service.*;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
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
    private final DeleteFriendService deleteFriendService;


    @PostMapping(value = "/meet", consumes = MediaType.MULTIPART_FORM_DATA_VALUE) // 친구 만나기
    public ResponseEntity<String> meetFriend(@RequestPart("data") PostFriendRequest request, @RequestPart(value = "file", required = false) MultipartFile file, HttpServletRequest httpServletRequest) {
        return postFriendService.execute(request, file, httpServletRequest);
    }

    @GetMapping("/list") // 친구 리스트 가져오기
    public ResponseEntity<List<FriendResponse>> getFriendList(HttpServletRequest httpServletRequest) {
        return getFriendListService.execute(httpServletRequest);
    }

    @PutMapping("/authority") // 봇 권한 바꾸기
    public ResponseEntity<String> setAuthority(@RequestBody UpdateAuthorityRequest request, HttpServletRequest httpServletRequest) {
        return updateAuthorityService.execute(request, httpServletRequest);
    }

    @GetMapping // 무료 친구 리스트 가져오기
    public ResponseEntity<List<FriendResponse>> getFreeFriendList() {
        return getFreeFriendListService.execute();
    }

    @DeleteMapping // 친구 떠나기
    public ResponseEntity<String> leftFriend(@RequestParam Long friendId) {
        return deleteFriendService.execute(friendId);
    }

}
