package miu.edu.cs489.shopping.onlineshoppingcart.dto.payment;

import java.time.LocalDate;

public record PaymentRequest(
        String paymentMethod,

        LocalDate paymentDate,

        Integer creditCardId
) {
}
