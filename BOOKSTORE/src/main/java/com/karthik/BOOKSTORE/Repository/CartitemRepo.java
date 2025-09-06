package com.karthik.BOOKSTORE.Repository;

import com.karthik.BOOKSTORE.Model.CartItems;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartitemRepo extends JpaRepository<CartItems,Long> {
    List<CartItems> findByCart_Cartid(Long cart_id);
    CartItems findByBook_Id(Long cart_id);
}
