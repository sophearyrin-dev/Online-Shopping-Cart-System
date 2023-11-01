package miu.edu.cs489.shopping.onlineshoppingcart.dto.orderline;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import miu.edu.cs489.shopping.onlineshoppingcart.dto.order.OrderResponse;
import miu.edu.cs489.shopping.onlineshoppingcart.dto.order.OrderResponseOnly;
import miu.edu.cs489.shopping.onlineshoppingcart.dto.product.ProductResponse;
import miu.edu.cs489.shopping.onlineshoppingcart.dto.product.ProductResponseWithoutCategory;
import miu.edu.cs489.shopping.onlineshoppingcart.model.Order;
import miu.edu.cs489.shopping.onlineshoppingcart.model.Product;

public record OrderLineResponse(

        Integer orderLineId,

        int quantity,

        double subtotal,

        ProductResponseWithoutCategory product,

        OrderResponseOnly order
) {
}
