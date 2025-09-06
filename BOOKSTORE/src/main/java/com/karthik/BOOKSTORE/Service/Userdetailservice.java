package com.karthik.BOOKSTORE.Service;

import com.karthik.BOOKSTORE.Model.User;
import com.karthik.BOOKSTORE.Model.Userprinciple;
import com.karthik.BOOKSTORE.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.nio.file.attribute.UserPrincipal;

@Service
public class Userdetailservice implements UserDetailsService {
    @Autowired
    UserRepo userrepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user =userrepo.findByUsername(username);
        if(user== null)
        {
            throw new UsernameNotFoundException("username not found!!");
        }
        else{
            return new Userprinciple(user);
        }

    }
}
