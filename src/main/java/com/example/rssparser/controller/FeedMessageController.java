package com.example.rssparser.controller;

import com.example.rssparser.entity.FeedMessage;
import com.example.rssparser.service.FeedMessageService;
import com.example.rssparser.service.RSSFeedParserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "${cross.origin.url}", maxAge = 3600)
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
    public ResponseEntity<List<FeedMessage>> getFeedMessages(
            @RequestParam(name = "page", defaultValue = "0") Integer pageNumber,
            @RequestParam(name = "size", defaultValue = "10") Integer pageSize,
            @RequestParam(name = "sort", defaultValue = "updatedDate") String sortBy,
            @RequestParam(name = "direction", defaultValue = "desc") String sortDirection){
        List<FeedMessage> response = feedMessageService.getDirectedFeedMessages(pageNumber,pageSize,sortDirection,sortBy);
        if(response.isEmpty()){
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }else{
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }

    @Scheduled(fixedRateString = "${feed.refresh.interval.ms}")
    public void startContinuesFeeding(){
        rssFeedParserService.readFeed();
    }

}
