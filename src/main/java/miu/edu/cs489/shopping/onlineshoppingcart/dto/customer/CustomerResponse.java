package miu.edu.cs489.shopping.onlineshoppingcart.dto.customer;

import miu.edu.cs489.shopping.onlineshoppingcart.dto.address.AddressResponse;

import java.time.LocalDate;

public record CustomerResponse(

        Integer patientId,

        String fistName,

        String lastName,

        String phoneNumber,

        String email,

        LocalDate dob,

        AddressResponse address
) {

}
