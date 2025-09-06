package com.karthik.BOOKSTORE.Repository;

import com.karthik.BOOKSTORE.Model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepo extends JpaRepository<Category,Long> {

    Category findByCategoryname(String genre);
}
