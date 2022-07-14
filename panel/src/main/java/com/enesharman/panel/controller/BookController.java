package com.enesharman.panel.controller;

import com.enesharman.panel.dto.AddBookRequest;
import com.enesharman.panel.dto.UpdateBookRequest;
import com.enesharman.panel.service.BookService;
import com.enesharman.util.result.Result;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Book;
import java.util.List;

@RestController
@RequestMapping("/api/book")
public class BookController {
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping("/add")
    public ResponseEntity<String> addBook(@RequestBody AddBookRequest addBookRequest){
        Result result = this.bookService.addBook(addBookRequest);
        if(result.getResult()){
            return ResponseEntity.ok(result.getMessage());
        }
        return ResponseEntity.badRequest().body(result.getMessage());
    }

    @PutMapping("/update")
    public ResponseEntity<String> updateBook(@RequestBody UpdateBookRequest updateBookRequest){
        Result result = this.bookService.updateBook(updateBookRequest);
        if(result.getResult()){
            return ResponseEntity.ok(result.getMessage());
        }
        return ResponseEntity.badRequest().body(result.getMessage());
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteBook(@RequestParam long bookId){
        Result result = this.bookService.deleteBook(bookId);
        if(result.getResult()){
            return ResponseEntity.ok(result.getMessage());
        }
        return ResponseEntity.badRequest().body(result.getMessage());
    }
}
