package com.investment.findfriend.domain.chat.domain;

import com.investment.findfriend.domain.user.domain.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class Chat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column
    private String userMessage;

    @Column
    private String replyMessage;

    @Column
    private LocalDateTime timestamp;
}
