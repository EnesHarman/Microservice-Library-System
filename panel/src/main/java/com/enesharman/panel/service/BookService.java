package com.enesharman.panel.service;

import com.enesharman.panel.dto.AddBookRequest;
import com.enesharman.panel.dto.UpdateBookRequest;
import com.enesharman.util.result.Result;

public interface BookService {
    Result addBook(AddBookRequest addBookRequest);

    Result updateBook(UpdateBookRequest updateBookRequest);

    Result deleteBook(long bookId);
}
