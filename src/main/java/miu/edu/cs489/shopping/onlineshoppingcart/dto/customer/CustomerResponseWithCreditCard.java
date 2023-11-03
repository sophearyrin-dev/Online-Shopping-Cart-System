package miu.edu.cs489.shopping.onlineshoppingcart.dto.customer;

import miu.edu.cs489.shopping.onlineshoppingcart.dto.creditcard.CreditCardResponseWithoutCustomer;

import java.time.LocalDate;
import java.util.List;

public record CustomerResponseWithCreditCard(
        Integer customerId,

        String fistName,

        String lastName,

        String phoneNumber,

        String email,

        LocalDate dob,

        List<CreditCardResponseWithoutCustomer> creditCards
) {
}
