package com.karthik.BOOKSTORE.Service;

import com.karthik.BOOKSTORE.Model.Book;
import com.karthik.BOOKSTORE.Model.Category;
import com.karthik.BOOKSTORE.Repository.BookRepo;
import com.karthik.BOOKSTORE.Repository.CategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepo catrepo;
    @Autowired
    private BookRepo bookrepo;
    public Category addcategory(Category category) {
        return catrepo.save(category);
    }

    public Category updateCategory(Category category) {
        Optional<Category> catego = catrepo.findById(category.getCategoryid());
        if(catego!= null){
            Category originalcategory = catego.get();
            originalcategory.setCategoryname(category.getCategoryname());
            return catrepo.save(originalcategory);
        }
        return null;
    }

    public Category deleteCategory(Long id) {
        List<Book> books = bookrepo.findByCategory_Categoryid(id);

        boolean hasStock = books.stream().anyMatch(book -> book.getStock() > 0);

        if (hasStock) {
            return null;
        }
        Optional<Category> categ = catrepo.findById(id);
        if(categ.isPresent())
        {
           Category originalcate = categ.get();
            catrepo.delete(originalcate);
            return originalcate;
        }

   return null;
    }

}
