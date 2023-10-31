package miu.edu.cs489.shopping.onlineshoppingcart.service.imp;

import miu.edu.cs489.shopping.onlineshoppingcart.dto.creditcard.CreditCardRequest;
import miu.edu.cs489.shopping.onlineshoppingcart.dto.creditcard.CreditCardResponse;
import miu.edu.cs489.shopping.onlineshoppingcart.dto.customer.CustomerRequestWithoutAddress;
import miu.edu.cs489.shopping.onlineshoppingcart.dto.customer.CustomerResponse;
import miu.edu.cs489.shopping.onlineshoppingcart.dto.customer.CustomerResponseWithoutAddress;
import miu.edu.cs489.shopping.onlineshoppingcart.exception.CustomerNotFoundException;
import miu.edu.cs489.shopping.onlineshoppingcart.model.CreditCard;
import miu.edu.cs489.shopping.onlineshoppingcart.model.Customer;
import miu.edu.cs489.shopping.onlineshoppingcart.repository.CreditCardRepository;
import miu.edu.cs489.shopping.onlineshoppingcart.repository.CustomerRepository;
import miu.edu.cs489.shopping.onlineshoppingcart.service.CreditCardService;
import miu.edu.cs489.shopping.onlineshoppingcart.service.CustomerService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CreditCardServiceImp implements CreditCardService {

    private CreditCardRepository creditCardRepository;
    private CustomerRepository customerRepository;
    private CustomerService customerService;

    public CreditCardServiceImp(CreditCardRepository creditCardRepository,
                                CustomerRepository customerRepository) {
        this.creditCardRepository = creditCardRepository;
        this.customerRepository = customerRepository;
    }

    @Override
    public List<CreditCardResponse> getAllCreditCards() {
        return creditCardRepository.findAll().stream()
                .map(c -> new CreditCardResponse(
                        c.getCreditCardId(),
                        c.getCcName(),
                        c.getCcNumber(),
                        c.getExpirationDate(),
                        c.getCvc(),
                        new CustomerResponseWithoutAddress(
                                c.getCustomer().getCustomerId(),
                                c.getCustomer().getFirstName(),
                                c.getCustomer().getLastName(),
                                c.getCustomer().getPhoneNumber(),
                                c.getCustomer().getEmail(),
                                c.getCustomer().getDob()
                        )
                )).collect(Collectors.toList());
    }

    @Override
    public CreditCardResponse addNewCreditCard(CreditCardRequest creditCardRequest){

        Optional<Customer> customer = customerRepository.findById(creditCardRequest.customerId());

        CreditCard creditCard = new CreditCard();
        if(!customer.isEmpty()){
            creditCard = new CreditCard(
                    creditCardRequest.ccName(),
                    creditCardRequest.ccNumber(),
                    creditCardRequest.expirationDate(),
                    creditCardRequest.cvc()
            );
        }
        creditCard.setCustomer(customer.get());

        CreditCard creditCardSaved = creditCardRepository.save(creditCard);


        CreditCardResponse creditCardResponse = new CreditCardResponse(
                creditCardSaved.getCreditCardId(),
                creditCardSaved.getCcName(),
                creditCardSaved.getCcNumber(),
                creditCardSaved.getExpirationDate(),
                creditCardSaved.getCvc(),
                new CustomerResponseWithoutAddress(
                        customer.get().getCustomerId(),
                        customer.get().getFirstName(),
                        customer.get().getLastName(),
                        customer.get().getPhoneNumber(),
                        customer.get().getEmail(),
                        customer.get().getDob()
                )
        );

        return creditCardResponse;
    }
}
