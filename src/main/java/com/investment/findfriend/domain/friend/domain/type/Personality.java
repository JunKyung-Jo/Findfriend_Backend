package com.investment.findfriend.domain.friend.domain.type;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Personality {
    EXTROVERTED("외향적인"),
    INTROVERTED("내향적인"),
    REALISTIC("현실적인"),
    CREATIVE("창의적인"),
    ORGANIZED("계획적인"),
    EASYGOING("자유분방한"),
    SENSITIVE("감성적인"),
    RATIONAL("이성적인");

    private final String description;
}
