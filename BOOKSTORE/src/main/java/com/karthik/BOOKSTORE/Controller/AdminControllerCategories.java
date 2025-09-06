package com.karthik.BOOKSTORE.Controller;

import com.karthik.BOOKSTORE.Model.Category;
import com.karthik.BOOKSTORE.Service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("Bookstore/admin")
@PreAuthorize("hasRole('ADMIN')")
public class AdminControllerCategories {
    @Autowired
    CategoryService catser;
    @PostMapping("/addcategory")
    public ResponseEntity<Category> addcategory(@RequestBody Category category)
    {
        Category addedcategory = catser.addcategory(category);
        return new ResponseEntity<>(addedcategory, HttpStatus.CREATED);
    }
    @PutMapping("/updatecategory")
    public ResponseEntity<Category> UpdateCategory(@RequestBody Category category)
    {
       Category categ = catser.updateCategory(category);
       if(categ!=null)
       {
           return new ResponseEntity<>(categ,HttpStatus.FOUND);
       }
       else{
           return new ResponseEntity<>(HttpStatus.NOT_FOUND);
       }

    }
    @DeleteMapping("/deletecategory/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id)
    {
        Category Categ = catser.deleteCategory(id);
        if (Categ != null) {
            return new ResponseEntity(HttpStatus.OK);
        }
        else{
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

}
