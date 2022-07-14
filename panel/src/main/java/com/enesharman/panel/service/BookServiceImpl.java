package com.enesharman.panel.service;

import com.enesharman.panel.dto.AddBookRequest;
import com.enesharman.panel.dto.UpdateBookRequest;
import com.enesharman.util.events.BookEvent;
import com.enesharman.util.events.EventTypes;
import com.enesharman.util.result.Result;
import com.enesharman.util.result.SuccessResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Service
@Slf4j
public class BookServiceImpl implements BookService{

    @Value(value = "${kafka.book-topic}")
    private String topicName;
    KafkaTemplate<String,BookEvent> kafkaTemplate;


    public BookServiceImpl(KafkaTemplate<String, BookEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public Result addBook(AddBookRequest addBookRequest) {

        BookEvent bookEvent = BookEvent.builder()
                .name(addBookRequest.getName())
                .ISBN(addBookRequest.getISBN())
                .author(addBookRequest.getAuthor())
                .pageCount(addBookRequest.getPageCount())
                .type(EventTypes.ADD)
                .build();

        ListenableFuture<SendResult<String, BookEvent>> future =
                kafkaTemplate.send(topicName, bookEvent);

        future.addCallback(new ListenableFutureCallback<SendResult<String, BookEvent>>() {
            @Override
            public void onFailure(Throwable ex) {
                log.error("Error while sending message: ",ex);
            }

            @Override
            public void onSuccess(SendResult<String, BookEvent> result) {
                log.info("Message sent successfully: ", result.getProducerRecord());
            }
        });

        return new SuccessResult("Book has been added successfully.");
    }

    @Override
    public Result updateBook(UpdateBookRequest updateBookRequest) {
        BookEvent bookEvent = BookEvent.builder()
                .id(updateBookRequest.getId())
                .name(updateBookRequest.getName())
                .ISBN(updateBookRequest.getISBN())
                .author(updateBookRequest.getAuthor())
                .pageCount(updateBookRequest.getPageCount())
                .type(EventTypes.UPDATE)
                .build();

        ListenableFuture<SendResult<String, BookEvent>> future =
                kafkaTemplate.send(topicName, bookEvent);

        future.addCallback(new ListenableFutureCallback<SendResult<String, BookEvent>>() {
            @Override
            public void onFailure(Throwable ex) {
                log.error("Error while sending message: ",ex);
            }

            @Override
            public void onSuccess(SendResult<String, BookEvent> result) {
                log.info("Message sent successfully: ", result.getProducerRecord());
            }
        });

        return new SuccessResult("Book has been updated successfully.");
    }

    @Override
    public Result deleteBook(long bookId) {
        BookEvent bookEvent = BookEvent.builder()
                .id(bookId)
                .type(EventTypes.DELETE)
                .build();

        ListenableFuture<SendResult<String, BookEvent>> future =
                kafkaTemplate.send(topicName, bookEvent);

        future.addCallback(new ListenableFutureCallback<SendResult<String, BookEvent>>() {
            @Override
            public void onFailure(Throwable ex) {
                log.error("Error while sending message: ",ex);
            }

            @Override
            public void onSuccess(SendResult<String, BookEvent> result) {
                log.info("Message sent successfully: ", result.getProducerRecord());
            }
        });

        return new SuccessResult("Book has been deleted successfully.");
    }
}
