package miu.edu.cs489.shopping.onlineshoppingcart.dto.payment;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import miu.edu.cs489.shopping.onlineshoppingcart.dto.creditcard.CreditCardResponse;
import miu.edu.cs489.shopping.onlineshoppingcart.dto.creditcard.CreditCardResponseWithoutCustomer;
import miu.edu.cs489.shopping.onlineshoppingcart.model.CreditCard;

import java.time.LocalDate;

public record PaymentResponse(

        Integer paymentId,

        String paymentMethod,

        LocalDate paymentDate,

        CreditCardResponseWithoutCustomer creditCard
) {
}
