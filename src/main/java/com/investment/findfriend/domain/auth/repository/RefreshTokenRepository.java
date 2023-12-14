package com.investment.findfriend.domain.auth.repository;

import com.investment.findfriend.domain.auth.domain.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshToken, String> {
    boolean existsByAccessToken(String accessToken);

    Optional<RefreshToken> findByEmail(String email);
}
