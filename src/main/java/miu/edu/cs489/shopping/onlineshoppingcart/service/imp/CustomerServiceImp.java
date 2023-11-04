package miu.edu.cs489.shopping.onlineshoppingcart.service.imp;

import miu.edu.cs489.shopping.onlineshoppingcart.dto.address.AddressResponse;
import miu.edu.cs489.shopping.onlineshoppingcart.dto.creditcard.CreditCardRequest;
import miu.edu.cs489.shopping.onlineshoppingcart.dto.creditcard.CreditCardResponseWithoutCustomer;
import miu.edu.cs489.shopping.onlineshoppingcart.dto.customer.CustomerRequest;
import miu.edu.cs489.shopping.onlineshoppingcart.dto.customer.CustomerResponse;
import miu.edu.cs489.shopping.onlineshoppingcart.dto.customer.CustomerResponseWithCreditCard;
import miu.edu.cs489.shopping.onlineshoppingcart.exception.CustomerNotFoundException;
import miu.edu.cs489.shopping.onlineshoppingcart.model.Address;
import miu.edu.cs489.shopping.onlineshoppingcart.model.CreditCard;
import miu.edu.cs489.shopping.onlineshoppingcart.model.Customer;
import miu.edu.cs489.shopping.onlineshoppingcart.repository.CreditCardRepository;
import miu.edu.cs489.shopping.onlineshoppingcart.repository.CustomerRepository;
import miu.edu.cs489.shopping.onlineshoppingcart.service.CustomerService;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImp implements CustomerService {
    private CustomerRepository customerRepository;
    private CreditCardRepository creditCardRepository;

    public CustomerServiceImp(CustomerRepository customerRepository,
                              CreditCardRepository creditCardRepository) {
        this.customerRepository = customerRepository;
        this.creditCardRepository = creditCardRepository;
    }

    @Override
    public List<CustomerResponse> getAllCustomers() {
        return customerRepository.findAll(Sort.by("lastName")).stream()
                .map(p -> new CustomerResponse(
                        p.getCustomerId(),
                        p.getFirstName(),
                        p.getLastName(),
                        p.getPhoneNumber(),
                        p.getEmail(),
                        p.getDob(),
                        new AddressResponse(
                                p.getAddress().getAddressId(),
                                p.getAddress().getStreet(),
                                p.getAddress().getCity(),
                                p.getAddress().getState(),
                                p.getAddress().getZipCode()
                        )
                )).collect(Collectors.toList());
    }

    @Override
    public CustomerResponse addNewCustomer(CustomerRequest customerRequest) {

        var newCustomer = new Customer(
                customerRequest.fistName(),
                customerRequest.lastName(),
                customerRequest.phoneNumber(),
                customerRequest.dob(),
                customerRequest.email(),
               new Address(
                customerRequest.address().street(),
                customerRequest.address().city(),
                customerRequest.address().state(),
                customerRequest.address().zipCode()
        ));

        var savedCustomer = customerRepository.save(newCustomer);

        CustomerResponse customerResponse = new CustomerResponse(
                savedCustomer.getCustomerId(),
                savedCustomer.getFirstName(),
                savedCustomer.getLastName(),
                savedCustomer.getPhoneNumber(),
                savedCustomer.getEmail(),
                savedCustomer.getDob(),
                new AddressResponse(
                        savedCustomer.getAddress().getAddressId(),
                        savedCustomer.getAddress().getStreet(),
                        savedCustomer.getAddress().getCity(),
                        savedCustomer.getAddress().getState(),
                        savedCustomer.getAddress().getZipCode()
                )
        );
        return customerResponse;
    }

    @Override
    public CustomerResponse getCustomerById(int customerId) throws CustomerNotFoundException {
        var customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new CustomerNotFoundException(
                        String.format("Error: Customer with Id, %d, is not found", customerId)));

        CustomerResponse patientResponse = new CustomerResponse(
                customer.getCustomerId(),
                customer.getFirstName(),
                customer.getLastName(),
                customer.getPhoneNumber(),
                customer.getEmail(),
                customer.getDob(),
                new AddressResponse(
                        customer.getAddress().getAddressId(),
                        customer.getAddress().getStreet(),
                        customer.getAddress().getCity(),
                        customer.getAddress().getState(),
                        customer.getAddress().getZipCode()
                )

        );
        return patientResponse;
    }

    @Override
    public CustomerResponse updateCustomerById(CustomerRequest customerRequest, int customerId)
            throws CustomerNotFoundException{

        var customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new CustomerNotFoundException(
                        String.format("Error: Customer with Id, %d, is not found", customerId)));


        customer.setFirstName(customerRequest.fistName());
        customer.setLastName(customerRequest.lastName());
        customer.setPhoneNumber(customerRequest.phoneNumber());
        customer.setEmail(customerRequest.email());
        customer.setDob(customerRequest.dob());

        customer.setAddress(new Address(
                customer.getAddress().getAddressId(),
                customerRequest.address().street(),
                customerRequest.address().city(),
                customerRequest.address().state(),
                customerRequest.address().zipCode()
        ));


        var updatedCustomer = customerRepository.save(customer);

        CustomerResponse patientResponse = new CustomerResponse(
                updatedCustomer.getCustomerId(),
                updatedCustomer.getFirstName(),
                updatedCustomer.getLastName(),
                updatedCustomer.getPhoneNumber(),
                updatedCustomer.getEmail(),
                updatedCustomer.getDob(),
                new AddressResponse(
                        updatedCustomer.getAddress().getAddressId(),
                        updatedCustomer.getAddress().getStreet(),
                        updatedCustomer.getAddress().getCity(),
                        updatedCustomer.getAddress().getState(),
                        updatedCustomer.getAddress().getZipCode()
                )
        );

        return patientResponse;
    }


    @Override
    public void deleteCustomerById(int customerId) throws CustomerNotFoundException{
        customerRepository.findById(customerId)
                .orElseThrow(() -> new CustomerNotFoundException(
                        String.format("Error: Customer with Id, %d, is not found", customerId)));

        customerRepository.deleteById(customerId);
    }

    @Override
    public CustomerResponseWithCreditCard addCustomerCreditCard(
            Integer customerId, CreditCardRequest creditCardRequest)
            throws CustomerNotFoundException{

        var customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new CustomerNotFoundException(
                        String.format("Error: Customer with Id, %d, is not found", customerId)
                ));

        CreditCard creditCard = new CreditCard(
                creditCardRequest.ccName(),
                creditCardRequest.ccNumber(),
                creditCardRequest.expirationDate(),
                creditCardRequest.cvc()
        );
        creditCard.setCustomer(customer);

        CreditCard creditCardSaved = creditCardRepository.save(creditCard);

        CustomerResponseWithCreditCard customerResponseWithCreditCard =
                new CustomerResponseWithCreditCard(
                        creditCardSaved.getCustomer().getCustomerId(),
                        creditCardSaved.getCustomer().getFirstName(),
                        creditCardSaved.getCustomer().getLastName(),
                        creditCardSaved.getCustomer().getPhoneNumber(),
                        creditCardSaved.getCustomer().getEmail(),
                        creditCardSaved.getCustomer().getDob(),
                        creditCardSaved.getCustomer().getCreditCards().stream().map(
                                cc -> new CreditCardResponseWithoutCustomer(
                                        cc.getCreditCardId(),
                                        cc.getCcName(),
                                        cc.getCcNumber(),
                                        cc.getExpirationDate(),
                                        cc.getCvc()
                                )
                        ).collect(Collectors.toList())
                );


        return customerResponseWithCreditCard;
    }

    @Override
    public Optional<Customer> findById(int customerId) {
        return customerRepository.findById(customerId);
    }
}
