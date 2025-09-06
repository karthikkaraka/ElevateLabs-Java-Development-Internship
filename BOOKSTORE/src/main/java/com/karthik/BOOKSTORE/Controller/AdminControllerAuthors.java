package com.karthik.BOOKSTORE.Controller;

import com.karthik.BOOKSTORE.Model.Author;
import com.karthik.BOOKSTORE.Service.AuthorService;
import org.apache.tomcat.util.file.ConfigurationSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ResourceBundle;

@RestController
@RequestMapping("Bookstore/admin")
@PreAuthorize("hasRole('ADMIN')")
public class AdminControllerAuthors {
    @Autowired
    AuthorService authser;
    @PostMapping("/addauthor")
      public ResponseEntity<Author> addauthor(@RequestBody Author author)
      {
          Author addedauthor = authser.addauthor(author);
          return new ResponseEntity<>(addedauthor, HttpStatus.CREATED);
      }
      @PostMapping("/updateauthor")
      public ResponseEntity<Author> updateauthor(@RequestBody Author author)
      {
          Author updatedauthor = authser.updateauthor(author);
          return new ResponseEntity<>(updatedauthor, HttpStatus.CREATED);
      }
      @DeleteMapping("/deleteauthor/{id}")
     public ResponseEntity<Author> deleteauthor(@PathVariable Long id){
        Author deleteAuthor  =  authser.deleteauthor(id);
        if(deleteAuthor!=null)
        {
            return new ResponseEntity<>(deleteAuthor,HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

}
