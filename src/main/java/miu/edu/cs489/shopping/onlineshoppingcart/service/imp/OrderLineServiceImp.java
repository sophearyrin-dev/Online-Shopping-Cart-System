package miu.edu.cs489.shopping.onlineshoppingcart.service.imp;

import miu.edu.cs489.shopping.onlineshoppingcart.dto.order.OrderResponseOnly;
import miu.edu.cs489.shopping.onlineshoppingcart.dto.orderline.OrderLineRequest;
import miu.edu.cs489.shopping.onlineshoppingcart.dto.orderline.OrderLineResponse;
import miu.edu.cs489.shopping.onlineshoppingcart.dto.product.ProductResponseWithoutCategory;
import miu.edu.cs489.shopping.onlineshoppingcart.model.Order;
import miu.edu.cs489.shopping.onlineshoppingcart.model.OrderLine;
import miu.edu.cs489.shopping.onlineshoppingcart.model.Product;
import miu.edu.cs489.shopping.onlineshoppingcart.repository.OrderLineRepository;
import miu.edu.cs489.shopping.onlineshoppingcart.repository.OrderRepository;
import miu.edu.cs489.shopping.onlineshoppingcart.repository.ProductRepository;
import miu.edu.cs489.shopping.onlineshoppingcart.service.OrderLineService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderLineServiceImp implements OrderLineService {

    private OrderLineRepository orderLineRepository;
    private ProductRepository productRepository;
    private OrderRepository orderRepository;

    public OrderLineServiceImp(OrderLineRepository orderLineRepository,
                               ProductRepository productRepository,
                               OrderRepository orderRepository) {
        this.orderLineRepository = orderLineRepository;
        this.productRepository = productRepository;
        this.orderRepository = orderRepository;
    }

    @Override
    public List<OrderLineResponse> findAllOrderLine() {
        return orderLineRepository.findAll().stream().map(
                orderLine -> new OrderLineResponse(
                        orderLine.getOrderLineId(),
                        orderLine.getQuantity(),
                        orderLine.getSubtotal(),
                        new ProductResponseWithoutCategory(
                                orderLine.getProduct().getProductId(),
                                orderLine.getProduct().getSKU(),
                                orderLine.getProduct().getDescription(),
                                orderLine.getProduct().getPrice(),
                                orderLine.getProduct().getStock()
                        ),
                        new OrderResponseOnly(
                                orderLine.getOrder().getOrderId(),
                                orderLine.getOrder().getOrderDate(),
                                orderLine.getOrder().getTotalPrice()
                        )
                )
        ).collect(Collectors.toList());
    }

    @Override
    public OrderLineResponse addNewOrderLine(OrderLineRequest orderLineRequest) {

        Optional<Product> product = productRepository.findById(orderLineRequest.productId());
        Optional<Order> order = orderRepository.findById(orderLineRequest.orderId());

        double unitPrice = product.get().getPrice();
        double subTotal = unitPrice * orderLineRequest.quantity();

        OrderLine addOrderLine = new OrderLine(
                orderLineRequest.quantity(),
                subTotal,
                product.get(),
                order.get()
        );

        OrderLine orderLineSaved = orderLineRepository.save(addOrderLine);

        OrderLineResponse orderLineResponse = new OrderLineResponse(
                orderLineSaved.getOrderLineId(),
                orderLineSaved.getQuantity(),
                orderLineSaved.getSubtotal(),
                new ProductResponseWithoutCategory(
                        orderLineSaved.getProduct().getProductId(),
                        orderLineSaved.getProduct().getSKU(),
                        orderLineSaved.getProduct().getDescription(),
                        orderLineSaved.getProduct().getPrice(),
                        orderLineSaved.getProduct().getStock()
                ),
                new OrderResponseOnly(
                        orderLineSaved.getOrder().getOrderId(),
                        orderLineSaved.getOrder().getOrderDate(),
                        orderLineSaved.getOrder().getTotalPrice()
                )
        );
        return orderLineResponse;
    }

    @Override
    public List<OrderLineResponse> findAllOrderLineByOrderId(Integer orderId) {

        List<OrderLine> orderLines = orderLineRepository.findAllByOrder_OrderId(orderId);

        return orderLines.stream()
                .map(orderLine -> new OrderLineResponse(
                        orderLine.getOrderLineId(),
                        orderLine.getQuantity(),
                        orderLine.getSubtotal(),
                        new ProductResponseWithoutCategory(
                                orderLine.getProduct().getProductId(),
                                orderLine.getProduct().getSKU(),
                                orderLine.getProduct().getDescription(),
                                orderLine.getProduct().getPrice(),
                                orderLine.getProduct().getStock()
                        ),
                        new OrderResponseOnly(
                                orderLine.getOrder().getOrderId(),
                                orderLine.getOrder().getOrderDate(),
                                orderLine.getOrder().getTotalPrice()
                        )
                ))
                .collect(Collectors.toList());
    }
}
