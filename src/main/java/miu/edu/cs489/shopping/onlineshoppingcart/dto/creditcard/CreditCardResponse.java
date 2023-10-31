package miu.edu.cs489.shopping.onlineshoppingcart.dto.creditcard;

import miu.edu.cs489.shopping.onlineshoppingcart.dto.customer.CustomerResponseWithoutAddress;
import java.time.LocalDate;

public record CreditCardResponse(
        Integer creditCardId,

        String ccName,

        String ccNumber,

        LocalDate expirationDate,

        String cvc,

        CustomerResponseWithoutAddress customer
) {
}
