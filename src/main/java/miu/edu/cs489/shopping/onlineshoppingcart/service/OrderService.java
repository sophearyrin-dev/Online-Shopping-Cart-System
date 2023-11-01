package miu.edu.cs489.shopping.onlineshoppingcart.service;

import miu.edu.cs489.shopping.onlineshoppingcart.dto.order.OrderRequest;
import miu.edu.cs489.shopping.onlineshoppingcart.dto.order.OrderResponse;
import miu.edu.cs489.shopping.onlineshoppingcart.dto.payment.PaymentResponse;

import java.util.List;

public interface OrderService {

    List<OrderResponse> findAllOrder();

    OrderResponse addNewOrder(OrderRequest orderRequest);

}
