package com.investment.findfriend.domain.feed.repository;

import com.investment.findfriend.domain.feed.domain.Feed;
import com.investment.findfriend.domain.friend.domain.Friend;
import com.investment.findfriend.domain.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FeedRepository extends JpaRepository<Feed, Long> {
    List<Feed> findAllByFriend(Friend friend);

    void deleteByIdAndUser(Long feedId, User user);
}
