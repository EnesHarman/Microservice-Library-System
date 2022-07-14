package com.enesharman.util.events;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookEvent {
    private long id;
    private String name;
    private String ISBN;
    private String author;
    private short pageCount;
    private EventTypes type;
}
