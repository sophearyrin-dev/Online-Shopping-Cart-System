package miu.edu.cs489.shopping.onlineshoppingcart.service.imp;

import miu.edu.cs489.shopping.onlineshoppingcart.dto.cart.CartRequest;
import miu.edu.cs489.shopping.onlineshoppingcart.dto.cart.CartResponse;
import miu.edu.cs489.shopping.onlineshoppingcart.dto.category.CategoryResponse;
import miu.edu.cs489.shopping.onlineshoppingcart.dto.customer.CustomerResponseWithoutAddress;
import miu.edu.cs489.shopping.onlineshoppingcart.dto.product.ProductResponse;
import miu.edu.cs489.shopping.onlineshoppingcart.model.Cart;
import miu.edu.cs489.shopping.onlineshoppingcart.model.Customer;
import miu.edu.cs489.shopping.onlineshoppingcart.model.Product;
import miu.edu.cs489.shopping.onlineshoppingcart.repository.CartRepository;
import miu.edu.cs489.shopping.onlineshoppingcart.repository.CustomerRepository;
import miu.edu.cs489.shopping.onlineshoppingcart.repository.ProductRepository;
import miu.edu.cs489.shopping.onlineshoppingcart.service.CartService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CartServiceImp implements CartService {

    private CartRepository cartRepository;
    private ProductRepository productRepository;
    private CustomerRepository customerRepository;

    public CartServiceImp(CartRepository cartRepository,
                          ProductRepository productRepository,
                          CustomerRepository customerRepository
    ) {
        this.cartRepository = cartRepository;
        this.productRepository = productRepository;
        this.customerRepository =customerRepository;
    }

    @Override
    public List<CartResponse> findAllCart() {
        return cartRepository.findAll().stream().map(
                cart -> new CartResponse(
                        cart.getCartId(),
                        new ProductResponse(
                                cart.getProduct().getProductId(),
                                cart.getProduct().getSKU(),
                                cart.getProduct().getDescription(),
                                cart.getProduct().getPrice(),
                                cart.getProduct().getStock(),
                                new CategoryResponse(
                                        cart.getProduct().getCategory().getCategoryId(),
                                        cart.getProduct().getCategory().getName()
                                )
                        ),
                        new CustomerResponseWithoutAddress(
                                cart.getCustomer().getCustomerId(),
                                cart.getCustomer().getFirstName(),
                                cart.getCustomer().getLastName(),
                                cart.getCustomer().getPhoneNumber(),
                                cart.getCustomer().getEmail(),
                                cart.getCustomer().getDob()
                        )
                )
        ).collect(Collectors.toList());
    }

    @Override
    public CartResponse addProductToCart(CartRequest cartRequest) {

        Optional<Product> productAddToCart = productRepository.
                findById(cartRequest.productId());
        Optional<Customer> customerAddToCart = customerRepository.
                findById(cartRequest.customerId());

        Cart cart1 = new Cart(productAddToCart.get(), customerAddToCart.get());

        Cart cart = cartRepository.save(cart1);

        CartResponse cartResponse = new CartResponse(
                cart.getCartId(),
                new ProductResponse(
                        cart.getProduct().getProductId(),
                        cart.getProduct().getSKU(),
                        cart.getProduct().getDescription(),
                        cart.getProduct().getPrice(),
                        cart.getProduct().getStock(),
                        new CategoryResponse(
                                cart.getProduct().getCategory().getCategoryId(),
                                cart.getProduct().getCategory().getName()
                        )
                ),
                new CustomerResponseWithoutAddress(
                        cart.getCustomer().getCustomerId(),
                        cart.getCustomer().getFirstName(),
                        cart.getCustomer().getLastName(),
                        cart.getCustomer().getPhoneNumber(),
                        cart.getCustomer().getEmail(),
                        cart.getCustomer().getDob()
                )
        );
        return cartResponse;
    }
}
