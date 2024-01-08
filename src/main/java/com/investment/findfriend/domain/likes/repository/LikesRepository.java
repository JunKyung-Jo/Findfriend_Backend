package com.investment.findfriend.domain.likes.repository;

import com.investment.findfriend.domain.feed.domain.Feed;
import com.investment.findfriend.domain.likes.domain.Likes;
import com.investment.findfriend.domain.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LikesRepository extends JpaRepository<Likes, Long> {
    Boolean existsByUserAndFeed(User user, Feed feed);

    void deleteByFeedIdAndUserId(Long feedId, Long userId);

    Long countByFeedId(Long feedId);

    void deleteAllByFeedId(Long id);
}
