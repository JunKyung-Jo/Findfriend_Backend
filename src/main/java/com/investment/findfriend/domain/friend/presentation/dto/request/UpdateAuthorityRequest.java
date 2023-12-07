package com.investment.findfriend.domain.friend.presentation.dto.request;

import com.investment.findfriend.domain.friend.domain.type.Authority;
import lombok.Getter;

@Getter
public class UpdateAuthorityRequest {
    private Long id;
    private Authority authority;
}
