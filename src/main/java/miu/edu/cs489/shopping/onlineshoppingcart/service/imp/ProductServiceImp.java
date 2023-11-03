package miu.edu.cs489.shopping.onlineshoppingcart.service.imp;

import miu.edu.cs489.shopping.onlineshoppingcart.dto.category.CategoryResponse;
import miu.edu.cs489.shopping.onlineshoppingcart.dto.product.ProductRequest;
import miu.edu.cs489.shopping.onlineshoppingcart.dto.product.ProductResponse;
import miu.edu.cs489.shopping.onlineshoppingcart.model.Category;
import miu.edu.cs489.shopping.onlineshoppingcart.model.Product;
import miu.edu.cs489.shopping.onlineshoppingcart.repository.CategoryRepository;
import miu.edu.cs489.shopping.onlineshoppingcart.repository.ProductRepository;
import miu.edu.cs489.shopping.onlineshoppingcart.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductServiceImp implements ProductService {

    private ProductRepository productRepository;
    private CategoryRepository categoryRepository;

    public ProductServiceImp(ProductRepository productRepository,
                             CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<ProductResponse> findAllProducts() {
        return productRepository.findAll().stream().map(
                product -> new ProductResponse(
                        product.getProductId(),
                        product.getSKU(),
                        product.getDescription(),
                        product.getPrice(),
                        product.getStock(),
                        new CategoryResponse(
                                product.getCategory().getCategoryId(),
                                product.getCategory().getName()
                        )
                )
        ).collect(Collectors.toList());
    }


}
