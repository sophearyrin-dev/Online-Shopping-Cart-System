package miu.edu.cs489.shopping.onlineshoppingcart.dto.address;

public record AddressResponse (
        Integer addressId,
        String street,
        String city,
        String state,
        String zipCode){
}
