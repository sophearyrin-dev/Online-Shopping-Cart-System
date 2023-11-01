package miu.edu.cs489.shopping.onlineshoppingcart.service.imp;

import miu.edu.cs489.shopping.onlineshoppingcart.dto.creditcard
        .CreditCardResponseWithoutCustomer;
import miu.edu.cs489.shopping.onlineshoppingcart.dto.payment.PaymentRequest;
import miu.edu.cs489.shopping.onlineshoppingcart.dto.payment.PaymentResponse;
import miu.edu.cs489.shopping.onlineshoppingcart.model.CreditCard;
import miu.edu.cs489.shopping.onlineshoppingcart.model.Payment;
import miu.edu.cs489.shopping.onlineshoppingcart.repository.CreditCardRepository;
import miu.edu.cs489.shopping.onlineshoppingcart.repository.PaymentRepository;
import miu.edu.cs489.shopping.onlineshoppingcart.service.PaymentService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PaymentServiceImp implements PaymentService {

    private PaymentRepository paymentRepository;
    private CreditCardRepository creditCardRepository;

    public PaymentServiceImp(PaymentRepository paymentRepository,CreditCardRepository creditCardRepository) {
        this.paymentRepository = paymentRepository;
        this.creditCardRepository = creditCardRepository;
    }

    @Override
    public List<PaymentResponse> getAllPayments() {
        return paymentRepository.findAll().stream().map(
                payment -> new PaymentResponse(
                        payment.getPaymentId(),
                        payment.getPaymentMethod(),
                        payment.getPaymentDate(),
                        new CreditCardResponseWithoutCustomer(
                                payment.getCreditCard().getCreditCardId(),
                                payment.getCreditCard().getCcName(),
                                payment.getCreditCard().getCcNumber(),
                                payment.getCreditCard().getExpirationDate(),
                                payment.getCreditCard().getCvc()
                        )
                )
        ).collect(Collectors.toList());
    }

    @Override
    public PaymentResponse addNewPayment(PaymentRequest paymentRequest) {

        Optional<CreditCard> creditCard = creditCardRepository.findById(paymentRequest.creditCardId());

        Payment paymentData = new Payment(paymentRequest.paymentMethod(), paymentRequest.paymentDate(),
                creditCard.get());

        Payment payment = paymentRepository.save(paymentData);

        PaymentResponse paymentResponse = new PaymentResponse(
                payment.getPaymentId(),
                payment.getPaymentMethod(),
                payment.getPaymentDate(),
                new CreditCardResponseWithoutCustomer(
                        payment.getCreditCard().getCreditCardId(),
                        payment.getCreditCard().getCcName(),
                        payment.getCreditCard().getCcNumber(),
                        payment.getCreditCard().getExpirationDate(),
                        payment.getCreditCard().getCvc()
                )
        );
        return paymentResponse;
    }
}
