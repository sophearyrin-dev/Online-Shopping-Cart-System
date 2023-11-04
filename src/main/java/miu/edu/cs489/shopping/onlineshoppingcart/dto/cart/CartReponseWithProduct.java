package miu.edu.cs489.shopping.onlineshoppingcart.dto.cart;

import miu.edu.cs489.shopping.onlineshoppingcart.dto.customer.CustomerResponseWithoutAddress;
import miu.edu.cs489.shopping.onlineshoppingcart.dto.product.ProductResponseWithoutCategory;
import miu.edu.cs489.shopping.onlineshoppingcart.model.Customer;
import miu.edu.cs489.shopping.onlineshoppingcart.model.Product;

import java.util.List;

public record CartReponseWithProduct(
        Integer cartId,
        List<ProductResponseWithoutCategory> products,
        CustomerResponseWithoutAddress customer
) {


}
