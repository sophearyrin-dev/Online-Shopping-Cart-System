package miu.edu.cs489.shopping.onlineshoppingcart.dto.order;

import miu.edu.cs489.shopping.onlineshoppingcart.dto.customer.CustomerResponseWithoutAddress;
import miu.edu.cs489.shopping.onlineshoppingcart.dto.payment.PaymentResponse;
import miu.edu.cs489.shopping.onlineshoppingcart.dto.shipment.ShipmentResponse;

import java.time.LocalDate;

public record OrderResponse(

        Integer orderId,

        LocalDate orderDate,

        double totalPrice,

        ShipmentResponse shipment,

        PaymentResponse payment,

        CustomerResponseWithoutAddress customer
) {
}
