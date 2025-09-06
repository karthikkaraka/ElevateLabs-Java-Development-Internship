package com.karthik.BOOKSTORE.Repository;

import com.karthik.BOOKSTORE.Model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BookRepo extends JpaRepository<Book,Long> {
    public List<Book> findByAuthor_Authorid(long authorid);
    public List<Book> findByCategory_Categoryid(long authorid);
    public List<Book> findByTitle(String title);

    public List<Book> findByISBN(Long isbn);
    @Query("SELECT b FROM Book b WHERE b.price >= :price")
    public List<Book> findbyprice(double price);
}
