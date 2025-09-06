package com.karthik.BOOKSTORE.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartItems {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long cartitemid;
    @ManyToOne
    @JoinColumn(name="cart_id",referencedColumnName = "cartid")
    @JsonIgnore
    @ToString.Exclude
    private Cart cart;
    @ManyToOne
    @JoinColumn(name="book_id",referencedColumnName = "id")
    @ToString.Exclude
    private Book book;
    private int quantity;
}
