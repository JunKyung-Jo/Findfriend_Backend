package com.investment.findfriend.domain.user.presentation.dto.response;

import com.investment.findfriend.domain.user.domain.type.Authority;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserResponse {
    private String name;
    private String statusMessage;
    private Authority authority;
    private String url;
}
