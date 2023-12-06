package com.investment.findfriend.domain.user.domain;

import com.investment.findfriend.domain.user.domain.type.Authority;
import com.investment.findfriend.domain.user.domain.type.Gender;
import com.investment.findfriend.domain.user.presentation.dto.request.UpdateUserInfoRequest;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

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

    public void update(UpdateUserInfoRequest request) {
        this.name = request.getName();
        this.statusMessage = request.getStatusMessage();
    }
}
