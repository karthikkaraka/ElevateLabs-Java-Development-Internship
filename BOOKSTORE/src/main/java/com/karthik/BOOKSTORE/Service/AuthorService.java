package com.karthik.BOOKSTORE.Service;

import com.karthik.BOOKSTORE.Model.Author;
import com.karthik.BOOKSTORE.Model.Book;
import com.karthik.BOOKSTORE.Repository.AuthorRepo;
import com.karthik.BOOKSTORE.Repository.BookRepo;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorService {
@Autowired
    AuthorRepo authrepo;
@Autowired
    BookRepo bookrepo;
    public Author addauthor(Author author) {
        return authrepo.save(author);
    }

    public Author updateauthor(Author author) {
        Optional<Author> dbauthor = authrepo.findById(author.getAuthorid());
        if(dbauthor.isPresent())
        {
            Author originalauthor = dbauthor.get();
            originalauthor.setBio(author.getBio());
            originalauthor.setAuthorname(author.getAuthorname());
            originalauthor.setEmail(author.getEmail());
            List<Book> books =  bookrepo.findByAuthor_Authorid(author.getAuthorid());
            originalauthor.setBooks(books);
            return authrepo.save(originalauthor);
        }
        else return null;
    }



    public Author deleteauthor(Long id) {
        Optional<Author> authorOpt = authrepo.findById(id);
        if (authorOpt.isPresent()) {
            Author authorr = authorOpt.get();
            List<Book> books = bookrepo.findByAuthor_Authorid(authorr.getAuthorid());


            boolean hasStock = books.stream().anyMatch(book -> book.getStock() > 0);

            if (hasStock) {

                return null;
            }
            authrepo.delete(authorr);
            return authorr;
        }
        return null;
    }

}
