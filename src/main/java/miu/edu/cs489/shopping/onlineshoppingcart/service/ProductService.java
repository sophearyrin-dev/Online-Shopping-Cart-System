package miu.edu.cs489.shopping.onlineshoppingcart.service;

import miu.edu.cs489.shopping.onlineshoppingcart.dto.product.ProductResponse;
import miu.edu.cs489.shopping.onlineshoppingcart.exception.ProductNotFoundException;
import miu.edu.cs489.shopping.onlineshoppingcart.model.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    List<ProductResponse> findAllProducts();

    List<ProductResponse> searchProducts(String quickSearch) throws ProductNotFoundException;

    void addToCart(Integer userId, Integer productId);
}
