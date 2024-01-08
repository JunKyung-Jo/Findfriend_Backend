package com.investment.findfriend.domain.feed.domain;

import com.investment.findfriend.domain.file.domain.File;
import com.investment.findfriend.domain.friend.domain.Friend;
import com.investment.findfriend.domain.likes.domain.Likes;
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

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "feed_tags", joinColumns = @JoinColumn(name = "feed_id"))
    @Column
    private List<String> tags;

    @ManyToOne
    private Friend friend;

    @OneToOne
    private File file;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Likes> likes;
}
