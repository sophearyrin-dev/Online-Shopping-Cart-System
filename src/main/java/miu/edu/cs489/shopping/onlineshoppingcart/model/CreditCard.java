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
@Table(name = "creditcards")
public class CreditCard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer creditCardId;

    private String ccName;

    private String ccNumber;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate expirationDate;

    private String cvc;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @OneToOne(mappedBy = "creditCard")
    private Payment payment;


    public CreditCard(String ccName, String ccNumber, LocalDate expirationDate, String cvc, Customer customer) {
        this.ccName = ccName;
        this.ccNumber = ccNumber;
        this.expirationDate = expirationDate;
        this.cvc = cvc;
        this.customer = customer;
    }

    public CreditCard(String ccName, String ccNumber, LocalDate expirationDate, String cvc) {
        this.ccName = ccName;
        this.ccNumber = ccNumber;
        this.expirationDate = expirationDate;
        this.cvc = cvc;
    }
}
