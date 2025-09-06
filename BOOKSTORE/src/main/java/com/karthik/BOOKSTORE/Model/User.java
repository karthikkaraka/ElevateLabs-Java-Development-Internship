package com.karthik.BOOKSTORE.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String username;
  private String password;
  @Enumerated(EnumType.STRING)
  private Role role;
  @OneToOne(mappedBy = "user" ,cascade=CascadeType.ALL)
  @ToString.Exclude
  @JsonIgnore
  @EqualsAndHashCode.Exclude
  @JsonManagedReference
  private Cart cart;
  @OneToMany(mappedBy = "user",cascade = CascadeType.ALL,orphanRemoval = true)
  @JsonIgnore
  private List<Order> orders;

}
