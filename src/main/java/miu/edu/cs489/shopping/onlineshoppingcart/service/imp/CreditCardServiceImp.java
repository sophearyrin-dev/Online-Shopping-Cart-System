package miu.edu.cs489.shopping.onlineshoppingcart.service.imp;

import miu.edu.cs489.shopping.onlineshoppingcart.dto.creditcard.CreditCardResponse;
import miu.edu.cs489.shopping.onlineshoppingcart.dto.customer.CustomerResponse;
import miu.edu.cs489.shopping.onlineshoppingcart.dto.customer.CustomerResponseWithoutAddress;
import miu.edu.cs489.shopping.onlineshoppingcart.repository.CreditCardRepository;
import miu.edu.cs489.shopping.onlineshoppingcart.service.CreditCardService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CreditCardServiceImp implements CreditCardService {

    private CreditCardRepository creditCardRepository;

    public CreditCardServiceImp(CreditCardRepository creditCardRepository) {
        this.creditCardRepository = creditCardRepository;
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
}
