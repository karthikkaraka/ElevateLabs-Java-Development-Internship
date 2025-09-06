package com.karthik.BOOKSTORE.Controller;

import com.karthik.BOOKSTORE.Model.User;
import com.karthik.BOOKSTORE.Service.JwtService;
import com.karthik.BOOKSTORE.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    UserService service;
    @Autowired
    AuthenticationManager authmanager;
    @Autowired
    JwtService jwtservice;
    @PostMapping("Bookstore/register")
   public ResponseEntity<User> register(@RequestBody User user)
    {
        User registeruser = service.registerUser(user);
        return new ResponseEntity<>(registeruser, HttpStatus.CREATED);
    }
    @PostMapping("Bookstore/login")
    public String login(@RequestBody User user)
    {
        Authentication auth = authmanager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(),user.getPassword()));
        if(auth!=null)
        {
            return jwtservice.generatejwttoken(user);
        }
        else return "no user found";
    }

}
