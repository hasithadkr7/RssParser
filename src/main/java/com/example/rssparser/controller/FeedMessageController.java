package com.example.rssparser.controller;

import com.example.rssparser.entity.FeedMessage;
import com.example.rssparser.service.FeedMessageService;
import com.example.rssparser.service.RSSFeedParserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
public class FeedMessageController {
    private final RSSFeedParserService rssFeedParserService;
    private final FeedMessageService feedMessageService;

    @Autowired
    public FeedMessageController(RSSFeedParserService rssFeedParserService, FeedMessageService feedMessageService) {
        this.rssFeedParserService = rssFeedParserService;
        this.feedMessageService = feedMessageService;
    }

    @GetMapping(path = "/items")
    @ResponseBody
    public List<FeedMessage> getFeedMessages(
            @RequestParam(name = "page", defaultValue = "0") Integer pageNumber,
            @RequestParam(name = "size", defaultValue = "10") Integer pageSize,
            @RequestParam(name = "sort", defaultValue = "updatedDate") String sortBy,
            @RequestParam(name = "direction", defaultValue = "desc") String sortDirection){
        return feedMessageService.getDirectedFeedMessages(pageNumber,pageSize,sortDirection,sortBy);
    }

    @Scheduled(fixedRateString = "${feed.refresh.interval.ms}")
    public void startContinuesFeeding(){
        rssFeedParserService.readFeed();
    }

}
