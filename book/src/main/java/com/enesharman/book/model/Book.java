package com.enesharman.book.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "books")
@Builder
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "name", columnDefinition = "varchar(100)")
    private String name;

    @Column(name = "isbn", columnDefinition = "varchar(20)",unique = true)
    private String isbn;

    @Column(name = "author", columnDefinition = "varchar(50)")
    private String author;

    @Column(name = "page_count")
    private short pageCount;

    @Column(name = "adding_date")
    private LocalDate addingDate;
}
