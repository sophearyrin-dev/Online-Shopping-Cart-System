package miu.edu.cs489.shopping.onlineshoppingcart.service;

import miu.edu.cs489.shopping.onlineshoppingcart.dto.creditcard.CreditCardRequest;
import miu.edu.cs489.shopping.onlineshoppingcart.dto.creditcard.CreditCardResponse;
import miu.edu.cs489.shopping.onlineshoppingcart.exception.CustomerNotFoundException;

import java.util.List;

public interface CreditCardService {

    List<CreditCardResponse> getAllCreditCards();

    CreditCardResponse addNewCreditCard(CreditCardRequest creditCardRequest) throws CustomerNotFoundException;

}
