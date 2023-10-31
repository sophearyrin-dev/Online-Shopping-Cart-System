package miu.edu.cs489.shopping.onlineshoppingcart.dto.customer;


import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import miu.edu.cs489.shopping.onlineshoppingcart.dto.address.AddressRequest;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public record CustomerRequest(

        String fistName,

        String lastName,

        String phoneNumber,

        @NotBlank(message = "Patient email is required and cannot be null or empty string or blank spaces")
        String email,

        @DateTimeFormat(pattern = "yyyy-MM-dd")
        LocalDate dob,

        @Valid AddressRequest address
) {
}
