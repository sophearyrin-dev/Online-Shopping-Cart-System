package miu.edu.cs489.shopping.onlineshoppingcart.dto.customer;

import jakarta.validation.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public record CustomerRequestWithoutAddress (
        String fistName,

        String lastName,

        String phoneNumber,

        @NotBlank(message = "Customer email is required and cannot be null or empty string or blank spaces")
        String email,

        @DateTimeFormat(pattern = "yyyy-MM-dd")
        LocalDate dob
){
}
