package com.karthik.BOOKSTORE.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Book {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private long id;
    private String title;
    private double price;
    private long stock;
    private String description;
    private long ISBN;
    private Date published_date;
    @ManyToOne
    @JoinColumn(name="authorid")
    private Author author;
    @ManyToOne
    @JoinColumn(name="categoryid")
    private Category category;
    @OneToMany(mappedBy = "book" ,cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Orderitem> orderitem;
}
