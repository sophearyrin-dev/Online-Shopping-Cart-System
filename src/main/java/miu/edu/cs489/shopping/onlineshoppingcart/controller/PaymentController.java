package miu.edu.cs489.shopping.onlineshoppingcart.controller;

import jakarta.validation.Valid;
import miu.edu.cs489.shopping.onlineshoppingcart.dto.payment.PaymentRequest;
import miu.edu.cs489.shopping.onlineshoppingcart.dto.payment.PaymentResponse;
import miu.edu.cs489.shopping.onlineshoppingcart.service.PaymentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/osc/api/v1/payment/")
public class PaymentController {

    private PaymentService paymentService;


    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @GetMapping("/list")
    public ResponseEntity<List<PaymentResponse>> getAllPayments(){
        return ResponseEntity.ok(paymentService.getAllPayments());
    }


    @PostMapping("/new")
    public ResponseEntity<PaymentResponse> addNewPayment(@RequestBody @Valid PaymentRequest paymentRequest){
        return new ResponseEntity<>(paymentService.addNewPayment(paymentRequest), HttpStatus.CREATED);
    }
}
