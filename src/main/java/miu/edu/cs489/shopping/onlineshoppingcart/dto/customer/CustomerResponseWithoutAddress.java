package miu.edu.cs489.shopping.onlineshoppingcart.dto.customer;

import java.time.LocalDate;

public record CustomerResponseWithoutAddress(
        Integer customerId,

        String fistName,

        String lastName,

        String phoneNumber,

        String email,

        LocalDate dob
) {
}
