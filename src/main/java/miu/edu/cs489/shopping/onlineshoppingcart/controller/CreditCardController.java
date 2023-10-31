package miu.edu.cs489.shopping.onlineshoppingcart.controller;

import miu.edu.cs489.shopping.onlineshoppingcart.dto.creditcard.CreditCardResponse;
import miu.edu.cs489.shopping.onlineshoppingcart.service.CreditCardService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/osc/api/v1/creditcard")
public class CreditCardController {

    private CreditCardService creditCardService;

    public CreditCardController(CreditCardService creditCardService) {
        this.creditCardService = creditCardService;
    }

    @GetMapping("/list")
    public ResponseEntity<List<CreditCardResponse>> getAllCreditCard(){
        return new ResponseEntity<>(creditCardService.getAllCreditCards(), HttpStatus.OK);
    }
}
