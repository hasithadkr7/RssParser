package com.example.rssparser.service;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

@Service
public class DateValidatorService {

    public LocalDateTime validateStrDate(String dateStr) {
        try {
            return LocalDateTime.from(DateTimeFormatter.RFC_1123_DATE_TIME.parse(dateStr));
        } catch (DateTimeParseException e) {
            return null;
        }
    }
}