package com.example.rssparser.service;

import com.example.rssparser.entity.FeedMessage;
import com.example.rssparser.repository.FeedMessageRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class FeedMessageServiceTest {
    @Mock  private FeedMessageRepository feedMessageRepository;
    private FeedMessageService feedMessageService;

    @BeforeEach
    void setUp(){
        feedMessageService = new FeedMessageService(feedMessageRepository);
    }

    @Test
    void updateRssFeedMessages() {
        String title = "Enforce the Subpoenas";
        FeedMessage feedMessage = FeedMessage.builder()
                .title(title)
                .feedDescription("Host Reed Galen is joined by Lincoln Project Senior Advisor Trygve")
                .updatedDate(LocalDateTime.from(DateTimeFormatter.RFC_1123_DATE_TIME.parse("Tue, 26 Oct 2021 01:17:19 +0000")))
                .build();
        feedMessageService.updateRssFeedMessages(feedMessage);
        verify(feedMessageRepository).save(feedMessage);
    }

    @Test
    @Disabled
    void getDirectedFeedMessages() {
        //when
        FeedMessage feedMessage = FeedMessage.builder()
                .title("Enforce the Subpoenas")
                .feedDescription("Host Reed Galen is joined by Lincoln Project Senior Advisor Trygve")
                .updatedDate(LocalDateTime.from(DateTimeFormatter.RFC_1123_DATE_TIME.parse("Tue, 26 Oct 2021 01:17:19 +0000")))
                .build();
        feedMessageService.updateRssFeedMessages(feedMessage);
        feedMessageService.getDirectedFeedMessages(0,10,"asc","updatedDate");
        //then
        Pageable pageable = PageRequest.of(0,10, Sort.by(Sort.Direction.fromString("asc"), "updatedDate"));
        verify(feedMessageRepository).findAll(pageable);

    }
}