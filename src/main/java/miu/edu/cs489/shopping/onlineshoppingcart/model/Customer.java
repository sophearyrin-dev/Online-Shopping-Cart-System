package miu.edu.cs489.shopping.onlineshoppingcart.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "customers")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int customerId;

    private String firstname;

    private String lastname;

    private String email;

    private LocalDate dob;

    private String phoneNumber;

    @OneToMany(mappedBy = "customer")
    private List<Address> addresses;

    @OneToMany(mappedBy = "customer")
    private List<Wishlist> wishlists;

    @OneToMany(mappedBy = "customer")
    private List<Cart> cart;

    @OneToOne(mappedBy = "customer")
    private CreditCard creditCard;

    @OneToMany(mappedBy = "customer")
    private List<Order> orders;


}
