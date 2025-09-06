package com.karthik.BOOKSTORE.DTO;

import com.karthik.BOOKSTORE.Model.CartItems;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cartresponse {
   private  List<CartItems> items;
}
