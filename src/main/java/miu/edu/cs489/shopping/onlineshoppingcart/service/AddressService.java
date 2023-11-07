package miu.edu.cs489.shopping.onlineshoppingcart.service;


import miu.edu.cs489.shopping.onlineshoppingcart.dto.address.AddressRequest;
import miu.edu.cs489.shopping.onlineshoppingcart.dto.address.AddressResponse;
import miu.edu.cs489.shopping.onlineshoppingcart.dto.address.AddressResponseClass;
import miu.edu.cs489.shopping.onlineshoppingcart.exception.AddressNotFoundException;

import java.util.List;

public interface AddressService {

    List<AddressResponseClass> getAllAddresses();

    AddressResponse addNewAddress(AddressRequest newAddress);

    AddressResponse getAddressById(Integer addressId) throws AddressNotFoundException;

    AddressResponse updateAddress(AddressRequest addressRequest, int addressId) throws AddressNotFoundException;

    void deleteAddressById(int addressId) throws AddressNotFoundException;

//    List<AddressPatientResponse> findAllAddressPatient();


}
