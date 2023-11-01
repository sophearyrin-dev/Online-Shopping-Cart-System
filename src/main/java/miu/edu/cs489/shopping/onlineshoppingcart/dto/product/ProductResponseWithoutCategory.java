package miu.edu.cs489.shopping.onlineshoppingcart.dto.product;

import miu.edu.cs489.shopping.onlineshoppingcart.dto.category.CategoryResponse;

public record ProductResponseWithoutCategory(

        Integer productId,

        String SKU,

        String description,

        double price,

        int stock

) {
}
