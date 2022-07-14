package com.enesharman.book.service;

import com.enesharman.book.model.Book;
import com.enesharman.book.repository.BookRepository;
import com.enesharman.util.events.BookEvent;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class BookServiceImpl implements BookService{
    private BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }


    @Override
    public void add(BookEvent message) {
        Book book = Book.builder()
                .name(message.getName())
                .isbn(message.getISBN())
                .author(message.getAuthor())
                .pageCount(message.getPageCount())
                .addingDate(LocalDate.now())
                .build();
        this.bookRepository.save(book);
        return;
    }

    @Override
    public void delete(BookEvent message) {
        this.bookRepository.deleteById(message.getId());
        return;
    }

    @Override
    public void update(BookEvent message) {
        Book book = Book.builder()
                .id(message.getId())
                .name(message.getName())
                .isbn(message.getISBN())
                .author(message.getAuthor())
                .pageCount(message.getPageCount())
                .build();
        this.bookRepository.save(book);
        return;
    }
}
