package miu.edu.cs489.shopping.onlineshoppingcart.service;

import miu.edu.cs489.shopping.onlineshoppingcart.dto.payment.PaymentRequest;
import miu.edu.cs489.shopping.onlineshoppingcart.dto.payment.PaymentResponse;

import java.util.List;

public interface PaymentService {

    List<PaymentResponse> getAllPayments();

    PaymentResponse addNewPayment(PaymentRequest paymentRequest);

}
