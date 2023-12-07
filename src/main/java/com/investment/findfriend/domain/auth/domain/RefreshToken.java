package com.investment.findfriend.domain.auth.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class RefreshToken {
    @Id
    private String email;

    @Column
    private String accessToken;

    @Column
    private String refreshToken;

    public void setToken(String accessToken, String refreshToken) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }
}
