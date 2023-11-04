package miu.edu.cs489.shopping.onlineshoppingcart.service.imp;

import miu.edu.cs489.shopping.onlineshoppingcart.dto.category.CategoryResponse;
import miu.edu.cs489.shopping.onlineshoppingcart.dto.product.ProductResponse;
import miu.edu.cs489.shopping.onlineshoppingcart.exception.ProductNotFoundException;
import miu.edu.cs489.shopping.onlineshoppingcart.model.Customer;
import miu.edu.cs489.shopping.onlineshoppingcart.model.Product;
import miu.edu.cs489.shopping.onlineshoppingcart.repository.CategoryRepository;
import miu.edu.cs489.shopping.onlineshoppingcart.repository.CustomerRepository;
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
    private CustomerRepository customerRepository;

    public ProductServiceImp(ProductRepository productRepository,
                             CategoryRepository categoryRepository,
                             CustomerRepository customerRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.customerRepository = customerRepository;
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

    @Override
    public List<ProductResponse> searchProducts(String quickSearch) throws ProductNotFoundException {

        List<Product> products =     productRepository.findProductBySKUContainingOrDescriptionContainingOrCategory_Name(
                        quickSearch,quickSearch,quickSearch
                );

        if (products.isEmpty()) {
            throw new ProductNotFoundException(String.format("Product is not found"));
        }

    return products.stream().map(
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

    @Override
    public void addToCart(Integer userId, Integer productId) {
        Optional<Customer> userOptional = customerRepository.findById(userId);
        Optional<Product> productOptional = productRepository.findById(productId);

        if (userOptional.isPresent() && productOptional.isPresent()) {
            Customer user = userOptional.get();
            Product product = productOptional.get();

//            user.getCarts().add(product);
            customerRepository.save(user);
        } else {
            throw new IllegalArgumentException("User or product not found.");
        }
    }

}
