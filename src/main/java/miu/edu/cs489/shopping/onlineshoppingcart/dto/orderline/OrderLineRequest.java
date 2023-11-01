package miu.edu.cs489.shopping.onlineshoppingcart.dto.orderline;

public record OrderLineRequest(

        int quantity,

        int productId,

        int orderId
) {

}
