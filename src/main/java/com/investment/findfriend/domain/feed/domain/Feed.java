package com.investment.findfriend.domain.feed.domain;

import com.investment.findfriend.domain.friend.domain.Friend;
import com.investment.findfriend.domain.user.domain.User;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class Feed {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String content;

    @Column
    @OneToMany(mappedBy = "feed")
    private List<User> tags;

    @OneToOne
    private Friend friend;
}
