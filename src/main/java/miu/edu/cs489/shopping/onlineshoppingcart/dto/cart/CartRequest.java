package miu.edu.cs489.shopping.onlineshoppingcart.dto.cart;

public record CartRequest(
        Integer productId,
        Integer customerId,
        int quantity
) {
}
