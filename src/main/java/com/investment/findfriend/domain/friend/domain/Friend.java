package com.investment.findfriend.domain.friend.domain;

import com.investment.findfriend.domain.chat.domain.Chat;
import com.investment.findfriend.domain.friend.domain.type.Authority;
import com.investment.findfriend.domain.friend.domain.type.Personality;
import com.investment.findfriend.domain.user.domain.User;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class Friend {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private String statusMessage;

    @Column
    private Authority authority;

    @Enumerated(EnumType.STRING)
    @ElementCollection
    private List<Personality> personalities;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "friend")
    private List<Chat> chats;

    public void setAuthority(Authority authority) {
        this.authority = authority;
    }
}
