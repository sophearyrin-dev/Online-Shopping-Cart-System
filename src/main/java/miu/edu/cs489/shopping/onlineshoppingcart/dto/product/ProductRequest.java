package miu.edu.cs489.shopping.onlineshoppingcart.dto.product;

import miu.edu.cs489.shopping.onlineshoppingcart.dto.category.CategoryRequest;
import miu.edu.cs489.shopping.onlineshoppingcart.model.Category;

public record ProductRequest(

        String SKU,

        String description,

        double price,

        int stock,

        Integer categoryId
) {
}
