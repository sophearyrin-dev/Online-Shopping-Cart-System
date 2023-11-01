package miu.edu.cs489.shopping.onlineshoppingcart.service;

import miu.edu.cs489.shopping.onlineshoppingcart.dto.orderline.OrderLineRequest;
import miu.edu.cs489.shopping.onlineshoppingcart.dto.orderline.OrderLineResponse;

import java.util.List;

public interface OrderLineService {

    List<OrderLineResponse> findAllOrderLine();

    OrderLineResponse addNewOrderLine(OrderLineRequest orderLineRequest);

    List<OrderLineResponse> findAllOrderLineByOrderId(Integer orderId);
}
