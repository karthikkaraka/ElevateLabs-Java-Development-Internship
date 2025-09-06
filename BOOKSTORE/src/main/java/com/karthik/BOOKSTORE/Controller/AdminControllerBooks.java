package com.karthik.BOOKSTORE.Controller;

import com.karthik.BOOKSTORE.DTO.BookCountRequest;
import com.karthik.BOOKSTORE.Model.Book;
import com.karthik.BOOKSTORE.Service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("Bookstore/admin")
@PreAuthorize("hasRole('ADMIN')")
public class AdminControllerBooks {
    @Autowired
    private BookService bookser;
    @PostMapping("/addbook")
    public ResponseEntity<Book> addnewbook(@RequestBody Book book)
    {
        Book addedbook = bookser.addbook(book);
        return new ResponseEntity<>(addedbook, HttpStatus.CREATED);
    }
    @PostMapping("/updatebook")
    public ResponseEntity<Book> updatedetailofbook(@RequestBody Book book)
    {
        Book updatebook = bookser.updatebook(book);
        if(updatebook!=null)
        {
            return new ResponseEntity<>(updatebook,HttpStatus.FOUND);
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @PostMapping("/setbookcount")
    public ResponseEntity<Book> setCountofbooks(@RequestBody BookCountRequest countrequest)
    {
        Book responcebook = bookser.setbookcount(countrequest);
        if(responcebook!= null)
        {
            return new ResponseEntity<>(responcebook,HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }
    @PostMapping("/softdelete/{id}")
    public ResponseEntity<Book> softdeletebook(@PathVariable long id)
    {
        Book deletebook = bookser.safedelete(id);
       if(deletebook!= null)
       {
           return new ResponseEntity<>(deletebook,HttpStatus.OK);
       }
       else{
           return new ResponseEntity<>(HttpStatus.NOT_FOUND);
       }
    }
}
