package miu.edu.cs489.shopping.onlineshoppingcart.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "payments")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer paymentId;

    private String paymentMethod;

    private LocalDate paymentDate;

    @OneToOne
    @JoinColumn(name = "credit_card_id")
    private CreditCard creditCard;

    @OneToOne(mappedBy = "payment")
    private Order order;

    public Payment(String paymentMethod, LocalDate paymentDate, CreditCard creditCard) {
        this.paymentMethod = paymentMethod;
        this.paymentDate = paymentDate;
        this.creditCard = creditCard;
    }
}
