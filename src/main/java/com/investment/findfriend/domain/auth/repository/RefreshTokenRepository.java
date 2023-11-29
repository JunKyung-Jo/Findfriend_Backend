package com.investment.findfriend.domain.auth.repository;

import com.investment.findfriend.domain.auth.domain.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
    boolean existsByAccessToken(String accessToken);
}
