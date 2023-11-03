package miu.edu.cs489.shopping.onlineshoppingcart.service;

import miu.edu.cs489.shopping.onlineshoppingcart.dto.category.CategoryRequest;
import miu.edu.cs489.shopping.onlineshoppingcart.dto.category.CategoryResponse;
import miu.edu.cs489.shopping.onlineshoppingcart.dto.category.CategoryResponseWithProduct;
import miu.edu.cs489.shopping.onlineshoppingcart.dto.product.ProductRequest;
import miu.edu.cs489.shopping.onlineshoppingcart.exception.CategoryNotFoundException;

import java.util.List;

public interface CategoryService {

    List<CategoryResponse> getAllCategories();

    CategoryResponse addNewCategory(CategoryRequest newCategory);

    CategoryResponseWithProduct addProductToCategory(
            Integer categoryId,
            ProductRequest productRequest) throws CategoryNotFoundException;

}
