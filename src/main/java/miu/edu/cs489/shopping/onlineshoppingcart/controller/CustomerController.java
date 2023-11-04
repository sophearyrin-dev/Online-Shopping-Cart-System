package miu.edu.cs489.shopping.onlineshoppingcart.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import miu.edu.cs489.shopping.onlineshoppingcart.dto.creditcard.CreditCardRequest;
import miu.edu.cs489.shopping.onlineshoppingcart.dto.customer.CustomerRequest;
import miu.edu.cs489.shopping.onlineshoppingcart.dto.customer.CustomerResponse;
import miu.edu.cs489.shopping.onlineshoppingcart.dto.customer.CustomerResponseWithCreditCard;
import miu.edu.cs489.shopping.onlineshoppingcart.exception.AddressNotFoundException;
import miu.edu.cs489.shopping.onlineshoppingcart.exception.CustomerNotFoundException;
import miu.edu.cs489.shopping.onlineshoppingcart.service.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/osc/api/v1/customer")
@SecurityRequirement(name = "onlineshopapi")
public class CustomerController {

    private CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/list")
    public ResponseEntity<List<CustomerResponse>> findAllCustomerIncludeAddress(){
        return new ResponseEntity<>(customerService.getAllCustomers(),
                HttpStatus.OK);
    }

    @PostMapping("/new")
    public ResponseEntity<CustomerResponse> addNewCustomer(@RequestBody CustomerRequest customerRequest){
        return new ResponseEntity<>(customerService.addNewCustomer(customerRequest),
                HttpStatus.CREATED);
    }

    @GetMapping("/{customerId}")
    public ResponseEntity<CustomerResponse> findCustomerById(@PathVariable int customerId) throws CustomerNotFoundException {
        return new ResponseEntity<>(customerService.getCustomerById(customerId), HttpStatus.FOUND);
    }

    @PutMapping("/update/{customerId}")
    public ResponseEntity<CustomerResponse> updateCustomerById(@RequestBody CustomerRequest customerRequest,
                                                               @PathVariable int customerId)
            throws CustomerNotFoundException{
        return new ResponseEntity<>(customerService.updateCustomerById(customerRequest,customerId),
                HttpStatus.ACCEPTED);
    }

//    @DeleteMapping("/delete/{customerId}")
//    public ResponseEntity<Void> deleteCustomerById(@PathVariable int customerId) throws CustomerNotFoundException{
//        customerService.deleteCustomerById(customerId);
//        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//    }

//    @GetMapping("/search/{searchString}")
//    public ResponseEntity<List<PatientResponse>> quickSearchPatient(@PathVariable String searchString){
//        return new ResponseEntity<>(customerService.searchPatient(searchString), HttpStatus.FOUND);
//    }

    @PostMapping("/{customerId}/creditcard")
    public ResponseEntity<CustomerResponseWithCreditCard> addNewCustomer(
            @PathVariable Integer customerId,
            @RequestBody CreditCardRequest creditCardRequest) throws CustomerNotFoundException {
        return new ResponseEntity<>(customerService.addCustomerCreditCard(customerId,creditCardRequest),
                HttpStatus.CREATED);
    }


}

