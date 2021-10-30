package com.example.rssparser.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class RSSFeedParserServiceTest {
    @Autowired
    private RSSFeedParserService rssFeedParserService;

    @Test
    public void readRssFeed(){
        rssFeedParserService.readFeed();
    }
}