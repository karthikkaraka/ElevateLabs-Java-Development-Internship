package com.karthik.BOOKSTORE.Repository;

import com.karthik.BOOKSTORE.Model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepo extends JpaRepository<Cart,Long> {
    public Cart findByUser_Id(long id);
}
