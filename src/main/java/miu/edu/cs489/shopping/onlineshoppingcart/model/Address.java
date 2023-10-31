package miu.edu.cs489.shopping.onlineshoppingcart.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "addresses")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer addressId;

    @Column(length = 20)
    private String street;

    @Column(length = 20)
    private String city;

    @Column(length = 20)
    private String state;

    @Column(length = 5)
    private String zipCode;

    @OneToOne(mappedBy = "address")
    private Customer customer;

    @OneToOne(mappedBy = "address")
    private Shipment shipment;

    public Address(String street, String city, String state, String zipCode) {
        this.street = street;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
    }
}
