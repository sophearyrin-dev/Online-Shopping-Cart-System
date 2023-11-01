package miu.edu.cs489.shopping.onlineshoppingcart.dto.cart;

import miu.edu.cs489.shopping.onlineshoppingcart.dto.customer.CustomerResponse;
import miu.edu.cs489.shopping.onlineshoppingcart.dto.customer.CustomerResponseWithoutAddress;
import miu.edu.cs489.shopping.onlineshoppingcart.dto.product.ProductResponse;

public record CartResponse(
        Integer cartId,
        ProductResponse product,
        CustomerResponseWithoutAddress customer

) {

}
