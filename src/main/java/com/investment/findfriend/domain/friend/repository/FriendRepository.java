package com.investment.findfriend.domain.friend.repository;

import com.investment.findfriend.domain.friend.domain.Friend;
import com.investment.findfriend.domain.friend.domain.type.Authority;
import com.investment.findfriend.domain.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FriendRepository extends JpaRepository<Friend, Long> {
    List<Friend> findByAuthorityInAndUserOrAuthorityAndUserIsNull(
            List<Authority> authorities,
            User user,
            Authority customAuthority
    );
}
