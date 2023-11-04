//package miu.edu.cs489.shopping.onlineshoppingcart.model;
//
//import jakarta.persistence.*;
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//import java.util.List;
//
//@Data
//@AllArgsConstructor
//@NoArgsConstructor
//@Entity
//@Table(name = "wishlists")
//public class Wishlist {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private int wishlistId;
//
//
//    @ManyToOne
//    @JoinColumn(name = "product_id")
//    private Product product;
//
//    @ManyToOne
//    @JoinColumn(name = "customer_id")
//    private Customer customer;
//
//}
