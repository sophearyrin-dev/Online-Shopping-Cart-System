package miu.edu.cs489.shopping.onlineshoppingcart.dto.order;


import java.time.LocalDate;

public record OrderResponseOnly(
        Integer orderId,

        LocalDate orderDate,

        double totalPrice
) {
}
