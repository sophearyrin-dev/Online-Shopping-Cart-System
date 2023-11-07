package miu.edu.cs489.shopping.onlineshoppingcart.dto.address;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public record AddressResponse (
        Integer addressId,
        String street,
        String city,
        String state,
        String zipCode){

    public AddressResponse(String street, String city, String state, String zipCode) {
        this(null, street, city, state, zipCode);
    }
    public AddressResponse(Integer addressId, String street, String city, String state, String zipCode) {
        this.addressId = addressId;
        this.street = street;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
    }
}
