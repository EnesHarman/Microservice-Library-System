package com.enesharman.book.service;

import com.enesharman.util.events.BookEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class BookConsumer {

    @Autowired
    private BookService bookService;

    @KafkaListener(topics = "book-topic")
    public void listenBooks (BookEvent message){
        log.info("Message Consumed.", message);
        switch (message.getType()){
            case ADD -> {
                bookService.add(message);
                break;
            }
            case DELETE -> {
                bookService.delete(message);
                break;
            }
            case UPDATE -> {
                bookService.update(message);
            }
            default -> {
                log.error("Message type is not valid!");
            }
        }

    }
}
