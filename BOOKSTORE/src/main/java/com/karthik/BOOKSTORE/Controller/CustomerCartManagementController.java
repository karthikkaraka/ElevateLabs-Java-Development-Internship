package com.karthik.BOOKSTORE.Controller;

import com.karthik.BOOKSTORE.DTO.Cartresponse;
import com.karthik.BOOKSTORE.Model.Cart;
import com.karthik.BOOKSTORE.Service.Cartservice;
import com.karthik.BOOKSTORE.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("Bookstore")
public class CustomerCartManagementController {
    @Autowired
    private Cartservice cartser;

    @Autowired
    UserRepo userrep;
    @PutMapping("/addtocart/{id}")
    public ResponseEntity<Cart> additemtocart(Authentication auth ,@PathVariable Long id)
    {
        System.out.println("karthik karaka");
        String username = auth.getName();
        Long userid = userrep.findByUsername(username).getId();
        Cart cart = cartser.additemtocart(id,userid);
        return new ResponseEntity<>(cart, HttpStatus.CREATED);
    }
    @GetMapping("/viewcart")
    public ResponseEntity<Cartresponse> viewCart(Authentication auth)
    {
        String username = auth.getName();
        Long userid = userrep.findByUsername(username).getId();
       Cartresponse cartresponse = cartser.viewcart(userid);
      return new ResponseEntity<>(cartresponse,HttpStatus.OK) ;
    }
    @DeleteMapping("cart/remove/item/{id}")
    public ResponseEntity<Void> removeitem(Authentication auth,@PathVariable Long id)
    {
        String username = auth.getName();
        Long userid = userrep.findByUsername(username).getId();
        cartser.removeitem(userid,id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
    @DeleteMapping("remove/cart")
    public ResponseEntity<Void> removecart(Authentication auth) throws ClassNotFoundException {
        String username = auth.getName();
        Long userid = userrep.findByUsername(username).getId();
        cartser.removecart(userid);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

}
