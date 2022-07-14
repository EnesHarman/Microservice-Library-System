package com.enesharman.book.service;

import com.enesharman.util.events.BookEvent;

public interface BookService {
    void add(BookEvent message);

    void delete(BookEvent message);


    void update(BookEvent message);
}
