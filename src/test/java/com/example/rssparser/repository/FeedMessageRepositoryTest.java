package com.example.rssparser.repository;

import com.example.rssparser.entity.FeedMessage;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@SpringBootTest
//@DataJpaTest
class FeedMessageRepositoryTest {
    @Autowired
    private FeedMessageRepository feedMessageRepository;

    @Test
    public void saveFeedMessage(){
        FeedMessage feedMessage = FeedMessage.builder()
                .title("Enforce the Subpoenas")
                .feedDescription("Host Reed Galen is joined by Lincoln Project Senior Advisor Trygve")
                .updatedDate(LocalDateTime.from(DateTimeFormatter.RFC_1123_DATE_TIME.parse("Tue, 26 Oct 2021 01:17:19 +0000")))
                .build();
        feedMessageRepository.save(feedMessage);
    }

    @Test
    public void getAllFeedMessages(){
        List<FeedMessage> feedMessageList = (List<FeedMessage>) feedMessageRepository.findAll();
        System.out.println("Feed Messages List : "+feedMessageList);
    }
}