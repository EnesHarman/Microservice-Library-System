package com.enesharman.panel.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AddBookRequest {
    private String name;
    private String ISBN;
    private String author;
    private short pageCount;
}
