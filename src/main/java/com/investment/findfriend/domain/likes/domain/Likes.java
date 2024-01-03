package com.investment.findfriend.domain.likes.domain;

import com.investment.findfriend.domain.feed.domain.Feed;
import com.investment.findfriend.domain.user.domain.User;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class Likes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Feed feed;

    @ManyToOne
    private User user;
}
