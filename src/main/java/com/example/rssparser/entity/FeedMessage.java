package com.example.rssparser.entity;

import lombok.*;
import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "tbl_feed_message")
public class FeedMessage {
    @Id
    @SequenceGenerator(
            name = "feed_message_sequence",
            sequenceName = "feed_message_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "feed_message_sequence"
    )
    private int id;

    @Column(nullable = false)
    private String title;

    @Lob
    private String feedDescription;

    private LocalDateTime updatedDate;
}
