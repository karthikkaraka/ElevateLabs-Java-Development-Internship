package com.karthik.BOOKSTORE.Service;

import com.karthik.BOOKSTORE.Model.User;
import com.karthik.BOOKSTORE.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepo userrepo;
    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);
    public User registerUser(User user) {
        user.setPassword(encoder.encode(user.getPassword()));
        return userrepo.save(user);
    }
}
