package miu.edu.cs489.shopping.onlineshoppingcart.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;

/**
 * Customer Model
 * @author sophearyrin
 * @version 1.0
 * @since Nov,2023
 */
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

    //Present here-----
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Cart> carts;

    @OneToMany(mappedBy = "customer")
    private List<CreditCard> creditCards;

    //----

    public Customer(String firstName, String lastName, String email, LocalDate dob, String phoneNumber, Address address) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.dob = dob;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }

    public Customer(String firstName, String lastName, String email, LocalDate dob, String phoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.dob = dob;
        this.phoneNumber = phoneNumber;
    }

    public Customer(Integer customerId) {
        this.customerId = customerId;
    }
}
