package miu.edu.cs489.shopping.onlineshoppingcart.service;

import miu.edu.cs489.shopping.onlineshoppingcart.dto.creditcard.CreditCardResponse;

import java.util.List;

public interface CreditCardService {

    List<CreditCardResponse> getAllCreditCards();

}
