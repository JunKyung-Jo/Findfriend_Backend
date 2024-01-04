package com.investment.findfriend.domain.user.domain;

import com.investment.findfriend.domain.chat.domain.Chat;
import com.investment.findfriend.domain.feed.domain.Feed;
import com.investment.findfriend.domain.friend.domain.Friend;
import com.investment.findfriend.domain.user.domain.type.Authority;
import com.investment.findfriend.domain.user.domain.type.Gender;
import com.investment.findfriend.domain.user.presentation.dto.request.UpdateUserInfoRequest;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private String email;

    @Column
    private LocalDate birthdate;

    @Column
    private Gender gender;

    @Column
    private Authority authority;

    @Column
    private String phone;

    @Column
    private String statusMessage;

    @Column
    private String url;

    @OneToMany
    private List<Friend> friends;

    @OneToMany(mappedBy = "user")
    private List<Chat> chats;

    @ManyToMany
    private List<Feed> feed;

    public void update(UpdateUserInfoRequest request, String url) {
        this.name = request.getName();
        this.statusMessage = request.getStatusMessage();
        this.url = url;
    }
}
