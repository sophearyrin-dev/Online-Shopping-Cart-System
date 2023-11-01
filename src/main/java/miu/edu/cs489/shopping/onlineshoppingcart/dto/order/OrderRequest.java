package miu.edu.cs489.shopping.onlineshoppingcart.dto.order;

import java.time.LocalDate;

public record OrderRequest(

        LocalDate orderDate,

        Integer shipmentId,

        Integer paymentId,

        Integer customerId
) {
}
