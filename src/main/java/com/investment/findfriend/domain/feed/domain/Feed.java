package com.investment.findfriend.domain.feed.domain;

import com.investment.findfriend.domain.friend.domain.Friend;
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
    private String url;

    @Column
    private List<String> tags;

    @ManyToOne
    private Friend friend;
}
