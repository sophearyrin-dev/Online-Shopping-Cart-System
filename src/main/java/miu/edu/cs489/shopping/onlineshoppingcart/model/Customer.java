package miu.edu.cs489.shopping.onlineshoppingcart.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

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
    private Integer customerId;

    private String firstName;

    private String lastName;

    private String email;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dob;

    private String phoneNumber;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "address_id", unique = true)
    private Address address;

    @OneToMany(mappedBy = "customer")
    private List<Wishlist> wishlists;

    @OneToMany(mappedBy = "customer")
    private List<Cart> cart;

    @OneToOne(mappedBy = "customer")
    private CreditCard creditCard;

    @OneToMany(mappedBy = "customer")
    private List<Order> orders;

    public Customer(String firstName, String lastName, String email, LocalDate dob, String phoneNumber, Address address) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.dob = dob;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }
}
