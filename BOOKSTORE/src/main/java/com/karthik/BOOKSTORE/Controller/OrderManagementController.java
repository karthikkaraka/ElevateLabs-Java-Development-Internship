package com.karthik.BOOKSTORE.Controller;

import com.karthik.BOOKSTORE.Model.Order;
import com.karthik.BOOKSTORE.Repository.OrderRepo;
import com.karthik.BOOKSTORE.Repository.UserRepo;
import com.karthik.BOOKSTORE.Service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("Bookstore")
public class OrderManagementController {
    @Autowired
    private UserRepo userrepo;
    @Autowired
    private OrderService orderser;
    @GetMapping("/placeOrder")
    public ResponseEntity<Order> placeOrder(Authentication auth)
    {
        System.out.println("karaka karthik");
        Long userid = userrepo.findByUsername(auth.getName()).getId();
        Order order = orderser.placeorder(userid);
        return new ResponseEntity<>(order, HttpStatus.OK);
    }
    @GetMapping("/getallOrders")
    public ResponseEntity<List<Order>> getallOrders(Authentication auth)
    {
        Long userid = userrepo.findByUsername(auth.getName()).getId();
        List<Order> orders = orderser.getallOrders(userid);
        return new ResponseEntity<>(orders,HttpStatus.FOUND);
    }
    @GetMapping("/getOrder/{id}")
    public ResponseEntity<Order> getorder(@PathVariable Long id)
    {
        Order order = orderser.getOrder(id);
        if(order!=null)
        {
            return new ResponseEntity<>(order,HttpStatus.FOUND);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
