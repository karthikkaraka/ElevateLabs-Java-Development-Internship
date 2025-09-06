package com.karthik.BOOKSTORE.Repository;

import com.karthik.BOOKSTORE.Model.Orderitem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderitemRepo extends JpaRepository<Orderitem,Long> {
}
