package com.karthik.BOOKSTORE.Service;

import com.karthik.BOOKSTORE.DTO.Cartresponse;
import com.karthik.BOOKSTORE.Model.Book;
import com.karthik.BOOKSTORE.Model.Cart;
import com.karthik.BOOKSTORE.Model.CartItems;
import com.karthik.BOOKSTORE.Model.User;
import com.karthik.BOOKSTORE.Repository.BookRepo;
import com.karthik.BOOKSTORE.Repository.CartRepo;
import com.karthik.BOOKSTORE.Repository.CartitemRepo;
import com.karthik.BOOKSTORE.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class Cartservice {
    @Autowired
    private CartRepo cartrepo;
    @Autowired
    private BookRepo bookRepo;
    @Autowired
    private CartitemRepo cartitemrepo;
    @Autowired
    UserRepo userrepo;
    public Cart additemtocart(Long id, Long userid) {
          Cart cart = cartrepo.findByUser_Id(userid);
          if(cart!=null)
          {
              List<CartItems> items =  cart.getItems();
              Optional<Book> book = bookRepo.findById(id);
              cart.setUpdatedat(new Date());
              if(book.isPresent())
              {
                  Book originalbook = book.get();
                  CartItems existingitem = items.stream().filter(item -> item.getBook().getId() == id).findFirst().orElse(null);
                     if(existingitem==null)
                     {
                         CartItems cartitem = new CartItems();
                         cartitem.setQuantity(1);
                         cartitem.setCart(cart);
                         cartitem.setBook(originalbook);
                         items.add(cartitem);
                         cartitemrepo.save(cartitem);
                     }
                     else
                     {
                         existingitem.setQuantity(existingitem.getQuantity()+1);
                         cartitemrepo.save(existingitem);
                     }
              }

              return cartrepo.save(cart);
          }
          else{
              //new cart
              Cart newcart = new Cart();
              newcart.setCreatedat(new Date());
              newcart.setUser(userrepo.findById(userid).get());
              Book book = bookRepo.findById(id).get();
              CartItems newCartitem = new CartItems();
              newCartitem.setBook(book);
              newCartitem.setQuantity(1);
              newCartitem.setCart(newcart);
              newcart.setItems(List.of(newCartitem));
              newcart.setUpdatedat(new Date());
              return cartrepo.save(newcart);
          }
    }

    public Cartresponse viewcart(Long userid) {
        Cart cart  = cartrepo.findByUser_Id(userid);
        Long id = cart.getCartid();
        List<CartItems> items = cartitemrepo.findByCart_Cartid(id);
        Cartresponse cartresponse = new Cartresponse();
        cartresponse.setItems(items);
        return cartresponse;
    }

    public void removeitem(Long userid, Long id) {

//        Long cartid = cart.getCartid();
        CartItems cartitem = cartitemrepo.findByBook_Id(id);
        cartitem.setQuantity(cartitem.getQuantity()-1);
        cartitemrepo.save(cartitem);
    }
    @Transactional
    public void removecart(Long userid) throws ClassNotFoundException {
        Cart cart  = cartrepo.findByUser_Id(userid);
        System.out.println(cart);
        if(cart!=null)
        {
            cartrepo.deleteById(cart.getCartid());
        }
        else{
            throw new ClassNotFoundException("Cart not found");
        }
    }
}
