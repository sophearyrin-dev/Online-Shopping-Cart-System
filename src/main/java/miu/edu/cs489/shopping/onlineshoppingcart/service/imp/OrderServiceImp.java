package miu.edu.cs489.shopping.onlineshoppingcart.service.imp;

import miu.edu.cs489.shopping.onlineshoppingcart.dto.address.AddressResponse;
import miu.edu.cs489.shopping.onlineshoppingcart.dto.creditcard.CreditCardResponseWithoutCustomer;
import miu.edu.cs489.shopping.onlineshoppingcart.dto.customer.CustomerResponseWithoutAddress;
import miu.edu.cs489.shopping.onlineshoppingcart.dto.order.OrderRequest;
import miu.edu.cs489.shopping.onlineshoppingcart.dto.order.OrderResponse;
import miu.edu.cs489.shopping.onlineshoppingcart.dto.payment.PaymentResponse;
import miu.edu.cs489.shopping.onlineshoppingcart.dto.shipment.ShipmentResponse;
import miu.edu.cs489.shopping.onlineshoppingcart.model.*;
import miu.edu.cs489.shopping.onlineshoppingcart.repository.*;
import miu.edu.cs489.shopping.onlineshoppingcart.service.OrderService;
import org.aspectj.weaver.ast.Or;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderServiceImp implements OrderService {

    private OrderRepository orderRepository;
    private ShipmentRepository shipmentRepository;
    private PaymentRepository paymentRepository;
    private CustomerRepository customerRepository;
    private OrderLineRepository orderLineRepository;

    public OrderServiceImp(OrderRepository orderRepository,
                           ShipmentRepository shipmentRepository,
                           PaymentRepository paymentRepository,
                           CustomerRepository customerRepository,
                           OrderLineRepository orderLineRepository

    ) {
        this.orderRepository = orderRepository;
        this.shipmentRepository = shipmentRepository;
        this.paymentRepository = paymentRepository;
        this.customerRepository = customerRepository;
        this.orderLineRepository = orderLineRepository;
    }

    @Override
    public List<OrderResponse> findAllOrder() {
        return orderRepository.findAll().stream().map(
                order -> new OrderResponse(
                        order.getOrderId(),
                        order.getOrderDate(),
                        order.getTotalPrice(),
                        new ShipmentResponse(
                                order.getShipment().getShipmentId(),
                                order.getShipment().getShipmentDate(),
                                new AddressResponse(
                                        order.getShipment().getAddress().getAddressId(),
                                        order.getShipment().getAddress().getStreet(),
                                        order.getShipment().getAddress().getCity(),
                                        order.getShipment().getAddress().getState(),
                                        order.getShipment().getAddress().getZipCode()
                                )
                        ),
                        new PaymentResponse(
                                order.getPayment().getPaymentId(),
                                order.getPayment().getPaymentMethod(),
                                order.getPayment().getPaymentDate(),
                                new CreditCardResponseWithoutCustomer(
                                        order.getPayment().getCreditCard().getCreditCardId(),
                                        order.getPayment().getCreditCard().getCcName(),
                                        order.getPayment().getCreditCard().getCcNumber(),
                                        order.getPayment().getCreditCard().getExpirationDate(),
                                        order.getPayment().getCreditCard().getCvc()
                                )
                        ),
                        new CustomerResponseWithoutAddress(
                                order.getCustomer().getCustomerId(),
                                order.getCustomer().getFirstName(),
                                order.getCustomer().getLastName(),
                                order.getCustomer().getPhoneNumber(),
                                order.getCustomer().getEmail(),
                                order.getCustomer().getDob()
                        )
                )
        ).collect(Collectors.toList());
    }

    @Override
    public OrderResponse addNewOrder(OrderRequest orderRequest) {

//        private ShipmentRepository shipmentRepository;
//        private PaymentRepository paymentRepository;
//        private CustomerRepository customerRepository;

        Optional<Shipment> shipmentAdd = shipmentRepository.findById(orderRequest.shipmentId());

        Optional<Payment> paymentAdd = paymentRepository.findById(orderRequest.paymentId());

        Optional<Customer> customerAdd = customerRepository.findById(orderRequest.customerId());

        double totalPrice = 0.0;

//        List<OrderLine> = orderLineRepository.findAllByOrder_OrderId(orderRequest.)

//        Order orderAdd = new Order(
//                orderRequest.orderDate(),
//
//        )

        return null;
    }

    @Override
    public OrderResponse getOrderByOrderId(Integer orderId) {

        List<OrderLine> orderLines = orderLineRepository.findAllByOrder_OrderId(orderId);
        System.out.println(orderLines);

        Double totalPrice = orderLines.stream().mapToDouble(
                p -> p.getSubtotal()
        ).sum();
        Optional<Order> order = orderRepository.findById(orderId);
        order.get().setTotalPrice(totalPrice);
        OrderResponse orderResponse = new OrderResponse(
                order.get().getOrderId(),
                order.get().getOrderDate(),
                order.get().getTotalPrice(),
                new ShipmentResponse(
                        order.get().getShipment().getShipmentId(),
                        order.get().getShipment().getShipmentDate(),
                        new AddressResponse(
                                order.get().getShipment().getAddress().getAddressId(),
                                order.get().getShipment().getAddress().getStreet(),
                                order.get().getShipment().getAddress().getCity(),
                                order.get().getShipment().getAddress().getState(),
                                order.get().getShipment().getAddress().getZipCode()
                        )
                ),
                new PaymentResponse(
                        order.get().getPayment().getPaymentId(),
                        order.get().getPayment().getPaymentMethod(),
                        order.get().getPayment().getPaymentDate(),
                        new CreditCardResponseWithoutCustomer(
                                order.get().getPayment().getCreditCard().getCreditCardId(),
                                order.get().getPayment().getCreditCard().getCcName(),
                                order.get().getPayment().getCreditCard().getCcNumber(),
                                order.get().getPayment().getCreditCard().getExpirationDate(),
                                order.get().getPayment().getCreditCard().getCvc()
                        )
                ),
                new CustomerResponseWithoutAddress(
                        order.get().getCustomer().getCustomerId(),
                        order.get().getCustomer().getFirstName(),
                        order.get().getCustomer().getLastName(),
                        order.get().getCustomer().getPhoneNumber(),
                        order.get().getCustomer().getEmail(),
                        order.get().getCustomer().getDob()
                )
        );


        return orderResponse;
    }
}
