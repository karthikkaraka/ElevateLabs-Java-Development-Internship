package com.karthik.BOOKSTORE.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long orderid;
    @ManyToOne
    @JoinColumn(name = "userid")
    private User user;
    private double totalanount;
    @Enumerated(EnumType.STRING)
    private Shipped status;
    private Date timestamp;
    @OneToMany(mappedBy = "order",cascade = CascadeType.ALL,orphanRemoval = true)
    @JsonIgnore
    private List<Orderitem> orderitems;
}
