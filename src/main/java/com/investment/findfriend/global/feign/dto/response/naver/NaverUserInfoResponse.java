package com.investment.findfriend.global.feign.dto.response.naver;

import lombok.Getter;

@Getter
public class NaverUserInfoResponse {
    private String name;
    private String email;
    private String gender;
    private String birthyear;
    private String birthday;
    private String mobile;
}
