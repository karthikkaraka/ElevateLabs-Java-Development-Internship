package com.karthik.BOOKSTORE.Repository;

import com.karthik.BOOKSTORE.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
