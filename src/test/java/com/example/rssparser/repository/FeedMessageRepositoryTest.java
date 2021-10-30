package com.example.rssparser.repository;

import com.example.rssparser.entity.FeedMessage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

@DataJpaTest
//@DataJpaTest
class FeedMessageRepositoryTest {
    @Autowired
    private FeedMessageRepository feedMessageRepository;

    @AfterEach
    void tearDown(){
        feedMessageRepository.deleteAll();
    }

    @Test
    public void findFeedMessageByTitleExists(){
        //given
        String title = "Enforce the Subpoenas";
        FeedMessage feedMessage = FeedMessage.builder()
                .title(title)
                .feedDescription("Host Reed Galen is joined by Lincoln Project Senior Advisor Trygve")
                .updatedDate(LocalDateTime.from(DateTimeFormatter.RFC_1123_DATE_TIME.parse("Tue, 26 Oct 2021 01:17:19 +0000")))
                .build();
        feedMessageRepository.save(feedMessage);
        //when
        Optional<FeedMessage> expected = feedMessageRepository.findFeedMessageByTitle(title);
        //then
        assertThat(expected.isPresent()).isTrue();
    }

    @Test
    public void findFeedMessageByTitleNotExists(){
        //given
        String title = "Enforce the Subpoenas";
        //when
        Optional<FeedMessage> expected = feedMessageRepository.findFeedMessageByTitle(title);
        //then
        assertThat(expected.isPresent()).isFalse();
    }

    @Test
    public void saveFeedMessage(){
        //given
        String title = "Enforce the Subpoenas";
        FeedMessage feedMessage = FeedMessage.builder()
                .title(title)
                .feedDescription("Host Reed Galen is joined by Lincoln Project Senior Advisor Trygve")
                .updatedDate(LocalDateTime.from(DateTimeFormatter.RFC_1123_DATE_TIME.parse("Tue, 26 Oct 2021 01:17:19 +0000")))
                .build();
        feedMessageRepository.save(feedMessage);
        //when
        Optional<FeedMessage> expected = feedMessageRepository.findFeedMessageByTitle(title);
        //then
        assertThat(expected.isPresent()).isTrue();
    }

    @Test
    public void getAllFeedMessages(){
        List<FeedMessage> feedMessageList = (List<FeedMessage>) feedMessageRepository.findAll();
        System.out.println("Feed Messages List : "+feedMessageList);
    }
}