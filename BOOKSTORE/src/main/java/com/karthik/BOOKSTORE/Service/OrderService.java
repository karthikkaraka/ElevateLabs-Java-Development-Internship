package com.karthik.BOOKSTORE.Service;

import com.karthik.BOOKSTORE.Model.*;
import com.karthik.BOOKSTORE.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderService {
    @Autowired
      private CartRepo cartrepo;
    @Autowired
     private CartitemRepo cartitemrepo;
    @Autowired
    private BookRepo bookrepo;
    @Autowired
    private UserRepo userrepo;
    @Autowired
    OrderRepo orderrepo;
    @Autowired
    OrderitemRepo orderitemrepo;
    public  Order placeorder(Long userid) {
       Cart cart = cartrepo.findByUser_Id(userid);
        List<CartItems> items = cartitemrepo.findByCart_Cartid(cart.getCartid());
        double totalamount = 0;
         for(CartItems ct: items ){
             Book book = bookrepo.findById(ct.getBook().getId()).get();
             totalamount =  totalamount +(book.getPrice()*ct.getQuantity());
         }
        System.out.println(items);
        Order order = new Order();
        order.setUser(userrepo.findById(userid).get());
        order.setStatus(Shipped.CONFIRMED);
        order.setTotalanount(totalamount);
        List<Orderitem> orderitems = items.stream().map( ct ->{
              Orderitem orderitem = new Orderitem();
              orderitem.setBook(ct.getBook());
              Book book = bookrepo.findById(ct.getBook().getId()).get();
              orderitem.setPrice(book.getPrice());
              orderitem.setQuantity(ct.getQuantity());
              orderitem.setOrder(order);
              return orderitem;
        }
        ).collect(Collectors.toList());
        order.setOrderitems(orderitems);
        order.setTimestamp(new Date());
        orderrepo.save(order);
       orderitemrepo.saveAll(orderitems);
       cartitemrepo.deleteAll(items);

        return order;
    }

    public List<Order> getallOrders(Long userid) {
        List<Order> orders =  orderrepo.findByUser_Id(userid);
        return orders;
    }

    public Order getOrder(Long id) {
        Optional<Order> order = orderrepo.findById(id);
        if(order.isPresent())
        {
            Order oriOrder = order.get();
            return oriOrder;
        }
        return null;
    }
}
