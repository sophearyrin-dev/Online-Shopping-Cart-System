package miu.edu.cs489.shopping.onlineshoppingcart.service.imp;

import miu.edu.cs489.shopping.onlineshoppingcart.dto.address.AddressRequest;
import miu.edu.cs489.shopping.onlineshoppingcart.dto.address.AddressResponse;
import miu.edu.cs489.shopping.onlineshoppingcart.exception.AddressNotFoundException;
import miu.edu.cs489.shopping.onlineshoppingcart.model.Address;
import miu.edu.cs489.shopping.onlineshoppingcart.repository.AddressRepository;
import miu.edu.cs489.shopping.onlineshoppingcart.service.AddressService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AddressServiceImp implements AddressService {

    private AddressRepository addressRepository;

    public AddressServiceImp(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    @Override
    public List<AddressResponse> getAllAddresses() {
        //originally get all address as list of address
        //return as list of address response
        //map from each originally object to address response

        return addressRepository.findAll()
                .stream()
                .map(a -> new AddressResponse(
                        a.getAddressId(),
                        a.getStreet(),
                        a.getCity(),
                        a.getState(),
                        a.getZipCode()
                )).collect(Collectors.toList());
    }

    @Override
    public AddressResponse addNewAddress(AddressRequest newAddress) {
        // return as AddressResponse
        //input as AddressRequest
        //originally return as Address Object

        //There are 2 step conversion
        // - from Requst to Entiry
        // - from Entity to Response
        Address address = new Address(
                newAddress.street(),
                newAddress.city(),
                newAddress.state(),
                newAddress.zipCode()
        );

        var addNewAddress = addressRepository.save(address);

        AddressResponse addressResponse = new AddressResponse(
                addNewAddress.getAddressId(),
                addNewAddress.getStreet(),
                addNewAddress.getCity(),
                addNewAddress.getState(),
                addNewAddress.getZipCode()
        );

        return addressResponse;
    }


    @Override
    public AddressResponse getAddressById(Integer addressId) throws AddressNotFoundException {

        var address = addressRepository.findById(addressId)
                .orElseThrow(() -> new AddressNotFoundException(
                        String.format("Error: Address with Id, %d, is not found", addressId)));

        AddressResponse addressResponse = new AddressResponse(
                address.getAddressId(),
                address.getStreet(),
                address.getCity(),
                address.getState(),
                address.getZipCode()
        );

        return addressResponse;
    }

    @Override
    public AddressResponse updateAddress(AddressRequest addressRequest, int addressId) throws AddressNotFoundException{

        var address = addressRepository.findById(addressId)
                .orElseThrow(() -> new AddressNotFoundException(
                        String.format("Error: Address with Id, %d, is not found", addressId)));

        address.setStreet(addressRequest.street());
        address.setCity(addressRequest.city());
        address.setState(addressRequest.state());
        address.setZipCode(addressRequest.zipCode());

        var addNewAddress = addressRepository.save(address);

        System.out.println(address.getStreet());

        AddressResponse addressResponse = new AddressResponse(
                addNewAddress.getAddressId(),
                addNewAddress.getStreet(),
                addNewAddress.getCity(),
                addNewAddress.getState(),
                addNewAddress.getZipCode()
        );

        return addressResponse;
    }

    @Override
    public void deleteAddressById(int addressId) throws AddressNotFoundException {
        addressRepository.findById(addressId)
                .orElseThrow(() -> new AddressNotFoundException(
                        String.format("Error: Address with Id, %d, is not found", addressId)));

        addressRepository.deleteById(addressId);
    }

//    @Override
//    public List<AddressPatientResponse> findAllAddressPatient() {
//        return addressRepository.findAll(Sort.by("city")).stream()
//                .map(p -> {
//                    if(p.getPatient() == null){
//                        return new AddressPatientResponse(
//                                p.getAddressId(),
//                                p.getStreet(),
//                                p.getCity(),
//                                p.getState(),
//                                p.getZipCode(),
//                                null);
//                    } else {
//                    return new AddressPatientResponse(
//                            p.getAddressId(),
//                            p.getStreet(),
//                            p.getCity(),
//                            p.getState(),
//                            p.getZipCode(),
//                            new PatientResponse2(
//                                    p.getPatient().getPatientId(),
//                                    p.getPatient().getFistName(),
//                                    p.getPatient().getLastName(),
//                                    p.getPatient().getPhoneNumber(),
//                                    p.getPatient().getEmail(),
//                                    p.getPatient().getDob()
//                            )
//                    );}
//                }
//                ).collect(Collectors.toList());
//    }

}
