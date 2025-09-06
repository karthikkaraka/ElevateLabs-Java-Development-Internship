package com.karthik.BOOKSTORE.Controller;

import com.karthik.BOOKSTORE.DTO.Bookresponse;
import com.karthik.BOOKSTORE.Model.Book;
import com.karthik.BOOKSTORE.Service.BrowsingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("Bookstore")
public class CustomerBrowsingController {
    @Autowired
   private BrowsingService broser;
    @GetMapping("/viewallbooks")
    public ResponseEntity<List<Bookresponse>> viewallbooks()
    {
        List<Bookresponse> books = broser.viewallbooks();
        return new ResponseEntity<>(books, HttpStatus.FOUND);
    }
    @GetMapping("/getbookbytitle/{title}")
    public ResponseEntity<List<Bookresponse>> findbookbytitle(@PathVariable String title)
    {
        List<Bookresponse> books  = broser.getbooksbytitle(title);
        return new ResponseEntity<>(books,HttpStatus.FOUND);
    }
    @GetMapping("/getbookbyauthor/{author}")
    public ResponseEntity<List<Bookresponse>> findbookbyauthor(@PathVariable String author)
    {
        List<Bookresponse> books  = broser.getbooksbyauthor(author);
        return new ResponseEntity<>(books,HttpStatus.FOUND);
    }
    @GetMapping("/getbookbyISBN/{isbn}")
    public ResponseEntity<List<Bookresponse>> findbookbyISBN(@PathVariable Long isbn)
    {
        List<Bookresponse> books  = broser.getbooksbyISBN(isbn);
        return new ResponseEntity<>(books,HttpStatus.FOUND);
    }

    //filtering books
    @GetMapping("/filterbookbygenre/{genre}")
    public ResponseEntity<List<Bookresponse>> filterbooksbygenre(@PathVariable String genre)
    {
        List<Bookresponse> books = broser.filterbooksbygenre(genre);
        return new ResponseEntity<>(books,HttpStatus.FOUND);
    }

    @GetMapping("/filterbookbyprice/{price}")
    public ResponseEntity<List<Bookresponse>> filterbooksbygenre(@PathVariable double price)
    {
        List<Bookresponse> books = broser.filterbooksbyprice(price);
        return new ResponseEntity<>(books,HttpStatus.FOUND);
    }
    @GetMapping("/viewbookdetails/{id}")
    public ResponseEntity<Book> getdetails(@PathVariable Long id)
    {
        Book book = broser.getbookdetail(id);
        return new ResponseEntity<>(book,HttpStatus.FOUND);
    }
    @GetMapping("/checkavailblity/{id}")
    public ResponseEntity<Boolean> checktheavailblity(@PathVariable Long id)
    {
       boolean isAvailble =  broser.checkavailblity(id);
       return new ResponseEntity<>(isAvailble,HttpStatus.FOUND);
    }

}
