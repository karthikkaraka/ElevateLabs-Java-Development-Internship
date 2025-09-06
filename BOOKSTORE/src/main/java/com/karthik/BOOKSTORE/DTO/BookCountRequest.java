package com.karthik.BOOKSTORE.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookCountRequest {
    private long book_id;
    private String bookname;
    private long book_count;
}
