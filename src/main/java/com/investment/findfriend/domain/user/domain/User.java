package com.investment.findfriend.domain.user.domain;

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

}
