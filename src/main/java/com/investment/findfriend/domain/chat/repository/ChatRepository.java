package com.investment.findfriend.domain.chat.repository;

import com.investment.findfriend.domain.chat.domain.Chat;
import com.investment.findfriend.domain.friend.domain.Friend;
import com.investment.findfriend.domain.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChatRepository extends JpaRepository<Chat, Long> {
    List<Chat> findAllByUserAndFriend(User user, Friend friend);
}
