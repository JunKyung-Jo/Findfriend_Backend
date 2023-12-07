package com.investment.findfriend.domain.friend.domain;

import com.investment.findfriend.domain.user.domain.User;
import jakarta.persistence.*;
import lombok.*;

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

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}
