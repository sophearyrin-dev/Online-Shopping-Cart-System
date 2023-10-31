package miu.edu.cs489.shopping.onlineshoppingcart.controller;

import jakarta.validation.Valid;
import miu.edu.cs489.shopping.onlineshoppingcart.dto.address.AddressRequest;
import miu.edu.cs489.shopping.onlineshoppingcart.dto.address.AddressResponse;
import miu.edu.cs489.shopping.onlineshoppingcart.exception.AddressNotFoundException;
import miu.edu.cs489.shopping.onlineshoppingcart.service.AddressService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/osc/api/v1/address/")
public class AddressController {

    private AddressService addressService;

    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @GetMapping("/list")
    public ResponseEntity<List<AddressResponse>> getAllAddresses(){
        return ResponseEntity.ok(addressService.getAllAddresses());
    }


    @PostMapping("/new")
    public ResponseEntity<AddressResponse> addNewAddress(@RequestBody @Valid AddressRequest addressRequest){
        return new ResponseEntity<>(addressService.addNewAddress(addressRequest), HttpStatus.CREATED);
    }

    @GetMapping("/{addressId}")
    public ResponseEntity<AddressResponse> getAddressById(@PathVariable Integer addressId)
    throws AddressNotFoundException {
        return ResponseEntity.ok(addressService.getAddressById(addressId));
    }


    @PutMapping("/update/{addressId}")
    public ResponseEntity<AddressResponse> updateAddressById(@RequestBody AddressRequest addressRequest,
                                                             @PathVariable int addressId) throws AddressNotFoundException{
        return new ResponseEntity<>(addressService.updateAddress(addressRequest,addressId),HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/delete/{addressId}")
    public ResponseEntity<Void> deleteAddressById(@PathVariable int addressId) throws AddressNotFoundException{
        addressService.deleteAddressById(addressId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

//    @GetMapping("/list")
//    public ResponseEntity<List<AddressPatientResponse>> getAllAddressPatients(){
//        return new ResponseEntity<>(addressService.findAllAddressPatient(), HttpStatus.FOUND);
//    }




}
