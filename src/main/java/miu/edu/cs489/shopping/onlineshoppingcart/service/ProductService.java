package miu.edu.cs489.shopping.onlineshoppingcart.service;

import miu.edu.cs489.shopping.onlineshoppingcart.dto.product.ProductRequest;
import miu.edu.cs489.shopping.onlineshoppingcart.dto.product.ProductResponse;

import java.util.List;

public interface ProductService {

    List<ProductResponse> findAllProducts();

}
