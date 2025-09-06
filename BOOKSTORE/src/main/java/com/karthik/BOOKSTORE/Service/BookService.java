package com.karthik.BOOKSTORE.Service;

import com.karthik.BOOKSTORE.DTO.BookCountRequest;
import com.karthik.BOOKSTORE.Model.Author;
import com.karthik.BOOKSTORE.Model.Book;
import com.karthik.BOOKSTORE.Model.Category;
import com.karthik.BOOKSTORE.Repository.AuthorRepo;
import com.karthik.BOOKSTORE.Repository.BookRepo;
import com.karthik.BOOKSTORE.Repository.CategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BookService {
    @Autowired
    private BookRepo bookrepo;
    @Autowired
    AuthorRepo authorrepo;
    @Autowired
    CategoryRepo categoryrepo;
    public Book addbook(Book book) {

        Author author = authorrepo.findById(book.getAuthor().getAuthorid())
                .orElseThrow(() -> new RuntimeException("Author not found"));
        Category category = categoryrepo.findById(book.getCategory().getCategoryid())
                .orElseThrow(() -> new RuntimeException("Category not found"));
        book.setAuthor(author);
        book.setCategory(category);
        bookrepo.save(book);
        return book;
    }

    public Book updatebook(Book book) {
        Optional<Book> dbbook = bookrepo.findById(book.getId());
        if(dbbook.isPresent())
        {
            Author author = authorrepo.findById(book.getAuthor().getAuthorid())
                    .orElseThrow(() -> new RuntimeException("Author not found"));
            Category category = categoryrepo.findById(book.getCategory().getCategoryid())
                    .orElseThrow(() -> new RuntimeException("Category not found"));
            Book OriginalBook = dbbook.get();
            OriginalBook.setAuthor(author);
            OriginalBook.setCategory(category);
            OriginalBook.setISBN(book.getISBN());
            OriginalBook.setPrice(book.getPrice());
            OriginalBook.setStock(book.getStock());
            OriginalBook.setDescription(book.getDescription());
            OriginalBook.setTitle(book.getTitle());
            OriginalBook.setPublished_date(book.getPublished_date());
             bookrepo.save(OriginalBook);
             return bookrepo.findById(OriginalBook.getId()).orElseThrow();
        }
        else{
            return null;
        }
    }

    public Book setbookcount(BookCountRequest countrequest) {
        Optional<Book> dbbook = bookrepo.findById(countrequest.getBook_id());
        if(dbbook.isPresent())
        {
            Book originalbook = dbbook.get();
            originalbook.setStock(countrequest.getBook_count());
            return bookrepo.save(originalbook);
        }
        else{
            return null;
        }
    }

    public Book safedelete(long id) {
        Optional<Book> dbbook = bookrepo.findById(id);
        if(dbbook.isPresent()) {
            Book originalbook = dbbook.get();
            originalbook.setStock(0);
            return bookrepo.save(originalbook);
        }
        else{
            return null;
        }
    }
}
