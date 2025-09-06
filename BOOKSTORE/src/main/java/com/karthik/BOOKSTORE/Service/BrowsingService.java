package com.karthik.BOOKSTORE.Service;

import com.karthik.BOOKSTORE.DTO.Bookresponse;
import com.karthik.BOOKSTORE.Model.Author;
import com.karthik.BOOKSTORE.Model.Book;
import com.karthik.BOOKSTORE.Repository.AuthorRepo;
import com.karthik.BOOKSTORE.Repository.BookRepo;
import com.karthik.BOOKSTORE.Repository.CategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;



@Service
public class BrowsingService {
@Autowired
private BookRepo bookrepo;
@Autowired
private AuthorRepo authrepo;
@Autowired
private CategoryRepo catrepo;

    public List<Bookresponse> viewallbooks() {
        List<Book> books = bookrepo.findAll();
        List<Bookresponse> bookresponces = books.stream().map(b->
        {
           Bookresponse response = new Bookresponse() ;
           response.setId(b.getId());
           Optional<Author> auth = authrepo.findById(b.getAuthor().getAuthorid());
           if(auth!=null)
           {
               Author originalauth = auth.get();
               response.setAuthorname(originalauth.getAuthorname());
           }
           response.setName(b.getTitle());
           return response;
        }).collect(Collectors.toList());
        return bookresponces;
    }

    public List<Bookresponse> getbooksbytitle(String title) {
        List<Book> books = bookrepo.findByTitle(title);
        List<Bookresponse> bookresponces = books.stream().map(b->
        {
            Bookresponse response = new Bookresponse() ;
            response.setId(b.getId());
            Optional<Author> auth = authrepo.findById(b.getAuthor().getAuthorid());
            if(auth!=null)
            {
                Author originalauth = auth.get();
                response.setAuthorname(originalauth.getAuthorname());
            }
            response.setName(b.getTitle());
            return response;
        }).collect(Collectors.toList());
        return bookresponces;
    }

    public List<Bookresponse> getbooksbyauthor(String author) {
        long authorid = authrepo.findByAuthorname(author).getAuthorid();
        List<Book> books = bookrepo.findByAuthor_Authorid(authorid);
        List<Bookresponse> bookresponces = books.stream().map(b->
        {
            Bookresponse response = new Bookresponse() ;
            response.setId(b.getId());
            Optional<Author> auth = authrepo.findById(b.getAuthor().getAuthorid());
            if(auth!=null)
            {
                Author originalauth = auth.get();
                response.setAuthorname(originalauth.getAuthorname());
            }
            response.setName(b.getTitle());
            return response;
        }).collect(Collectors.toList());
        return bookresponces;
    }

    public List<Bookresponse> getbooksbyISBN(Long isbn) {
        List<Book> books = bookrepo.findByISBN(isbn);
        List<Bookresponse> bookresponces = books.stream().map(b->
        {
            Bookresponse response = new Bookresponse() ;
            response.setId(b.getId());
            Optional<Author> auth = authrepo.findById(b.getAuthor().getAuthorid());
            if(auth!=null)
            {
                Author originalauth = auth.get();
                response.setAuthorname(originalauth.getAuthorname());
            }
            response.setName(b.getTitle());
            return response;
        }).collect(Collectors.toList());
        return bookresponces;
    }

    public List<Bookresponse> filterbooksbygenre(String genre) {
        Long categoryid = catrepo.findByCategoryname(genre).getCategoryid();
        List<Book> books = bookrepo.findByCategory_Categoryid(categoryid);
        List<Bookresponse> bookresponces = books.stream().map(b->
        {
            Bookresponse response = new Bookresponse() ;
            response.setId(b.getId());
            Optional<Author> auth = authrepo.findById(b.getAuthor().getAuthorid());
            if(auth!=null)
            {
                Author originalauth = auth.get();
                response.setAuthorname(originalauth.getAuthorname());
            }
            response.setName(b.getTitle());
            return response;
        }).collect(Collectors.toList());
        return bookresponces;
    }

    public List<Bookresponse> filterbooksbyprice(double price) {
        List<Book> books = bookrepo.findbyprice(price);
        List<Bookresponse> bookresponces = books.stream().map(b->
        {
            Bookresponse response = new Bookresponse() ;
            response.setId(b.getId());
            Optional<Author> auth = authrepo.findById(b.getAuthor().getAuthorid());
            if(auth!=null)
            {
                Author originalauth = auth.get();
                response.setAuthorname(originalauth.getAuthorname());
            }
            response.setName(b.getTitle());
            return response;
        }).collect(Collectors.toList());
        return bookresponces;
    }

    public Book getbookdetail(Long id) {
        Optional<Book> book = bookrepo.findById(id);
        if(book.isPresent()){
           Book Originalbook = book.get();
           return Originalbook;
        }
        return null;
    }

    public boolean checkavailblity(Long id) {
        Optional<Book> book = bookrepo.findById(id);
        int stock = 0;
        if(book.isPresent()) {
             stock = (int) book.get().getStock();
        }
        else{
            return false;
        }
        if(stock!=0)
        {
            return true;
        }
        return false;
    }
}
