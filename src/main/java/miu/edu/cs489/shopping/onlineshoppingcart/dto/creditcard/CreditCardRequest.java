package miu.edu.cs489.shopping.onlineshoppingcart.dto.creditcard;

import miu.edu.cs489.shopping.onlineshoppingcart.dto.customer.CustomerRequest;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public record CreditCardRequest(

        Integer customerId,
        String ccName,

        String ccNumber,

        @DateTimeFormat(pattern = "yyyy-MM-dd")
        LocalDate expirationDate,
        String cvc,

        CustomerRequest customerRequest

) {
}
