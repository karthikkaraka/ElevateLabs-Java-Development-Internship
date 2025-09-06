package com.karthik.BOOKSTORE.Repository;

import com.karthik.BOOKSTORE.Model.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepo extends JpaRepository<Author,Long> {
    Author findByAuthorname(String author);
}
