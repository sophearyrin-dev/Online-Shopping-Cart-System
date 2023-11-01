package miu.edu.cs489.shopping.onlineshoppingcart.dto.creditcard;
import java.time.LocalDate;

public record CreditCardResponseWithoutCustomer(
        Integer creditCardId,

        String ccName,

        String ccNumber,

        LocalDate expirationDate,

        String cvc
) {
}
