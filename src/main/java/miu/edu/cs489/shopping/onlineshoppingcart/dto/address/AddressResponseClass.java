package miu.edu.cs489.shopping.onlineshoppingcart.dto.address;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddressResponseClass{

    Integer addressId;
    String street;
    String city;
    String state;
    String zipCode;

}
