package miu.edu.cs489.shopping.onlineshoppingcart.dto.category;

import miu.edu.cs489.shopping.onlineshoppingcart.dto.product.ProductResponseWithoutCategory;

import java.util.List;

public record CategoryResponseWithProduct(
        Integer categoryId,
        String name,
        List<ProductResponseWithoutCategory> product
) {
}
