package com.example.rssparser.service;

import com.example.rssparser.entity.FeedMessage;
import com.example.rssparser.repository.FeedMessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FeedMessageService {
    private final FeedMessageRepository feedMessageRepository;
    @Autowired
    public FeedMessageService(FeedMessageRepository feedMessageRepository){
        this.feedMessageRepository = feedMessageRepository;
    }

    public void updateRssFeedMessages(FeedMessage latestFeedMessage){
        Optional<FeedMessage> feedMessageOptional = feedMessageRepository.findFeedMessageByTitle(latestFeedMessage.getTitle());
        if(feedMessageOptional.isPresent()){
            FeedMessage existingFeedMessage = feedMessageOptional.get();
            if (existingFeedMessage.getUpdatedDate().equals(latestFeedMessage.getUpdatedDate())){
                System.out.println("No updates from news title : " + existingFeedMessage.getTitle());
            }else {
                existingFeedMessage.setFeedDescription(latestFeedMessage.getFeedDescription());
                existingFeedMessage.setUpdatedDate(latestFeedMessage.getUpdatedDate());
                feedMessageRepository.save(existingFeedMessage);
            }
        }else {
            feedMessageRepository.save(latestFeedMessage);
        }
    }

    public List<FeedMessage> getDirectedFeedMessages(int pageNumber,int pageSize, String sortDirection, String sortBy){
        Pageable pageable = PageRequest.of(pageNumber,pageSize, Sort.by(Sort.Direction.fromString(sortDirection), sortBy));
        System.out.println("FeedMessageService|getDirectedFeedMessages|pageable:"+pageable);
        Page<FeedMessage> page = feedMessageRepository.findAll(pageable);
        if (page.hasContent()){
            System.out.println("FeedMessageService|getDirectedFeedMessages|page size:"+page.getSize());
            return page.getContent();
        }else {
            return new ArrayList<FeedMessage>();
        }
    }

}
