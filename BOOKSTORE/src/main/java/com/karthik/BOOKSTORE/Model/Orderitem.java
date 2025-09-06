package com.karthik.BOOKSTORE.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Orderitem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id ;
    @JoinColumn(name="orderid")
    @ManyToOne
    private Order order;
    @JoinColumn(name="bookid")
    @ManyToOne
    private Book book;
    private int Quantity;
    private double price;
}
