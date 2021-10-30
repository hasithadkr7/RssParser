package com.example.rssparser.repository;

import com.example.rssparser.entity.FeedMessage;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FeedMessageRepository extends PagingAndSortingRepository<FeedMessage, Integer> {
    @Query("select m from FeedMessage m where m.title = ?1")
    Optional<FeedMessage> findFeedMessageByTitle(String title);

}
