package com.karthik.BOOKSTORE.Repository;

import com.karthik.BOOKSTORE.Model.Cart;
import com.karthik.BOOKSTORE.Model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepo extends JpaRepository<Order,Long> {
    public List<Order> findByUser_Id(long id);
}
