package miu.edu.cs489.shopping.onlineshoppingcart.service.imp;

import miu.edu.cs489.shopping.onlineshoppingcart.dto.cart.CartRequest;
import miu.edu.cs489.shopping.onlineshoppingcart.dto.cart.CartResponse;
import miu.edu.cs489.shopping.onlineshoppingcart.dto.category.CategoryResponse;
import miu.edu.cs489.shopping.onlineshoppingcart.dto.customer.CustomerResponseWithoutAddress;
import miu.edu.cs489.shopping.onlineshoppingcart.dto.product.ProductResponse;
import miu.edu.cs489.shopping.onlineshoppingcart.exception.CustomerNotFoundException;
import miu.edu.cs489.shopping.onlineshoppingcart.exception.ProductNotFoundException;
import miu.edu.cs489.shopping.onlineshoppingcart.model.Cart;
import miu.edu.cs489.shopping.onlineshoppingcart.repository.CartRepository;
import miu.edu.cs489.shopping.onlineshoppingcart.repository.CustomerRepository;
import miu.edu.cs489.shopping.onlineshoppingcart.repository.ProductRepository;
import miu.edu.cs489.shopping.onlineshoppingcart.service.CartService;
import org.springframework.stereotype.Service;

import java.util.List;
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

    //Presentation - Add Product To Cart
    @Override
    public void addToCart(CartRequest cartRequest) throws ProductNotFoundException, CustomerNotFoundException {
        //procut, customer
        Integer productId = cartRequest.productId();
        Integer customerId = cartRequest.customerId();

        var product = productRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException(
                    String.format("Error: Product with Id, %d, is not found", productId)
            ));

        var customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new CustomerNotFoundException(
                        String.format("Error: Customer with Id, %d, is not found", customerId)
                ));

        //Reduce Stock
        Integer stock = product.getStock();
        System.out.println(stock);
        Integer productQuantity = cartRequest.quantity();

        if(stock <=0){
            throw new ProductNotFoundException("Alert: Sorry, Product out of stock");
        }
        product.setStock(stock - productQuantity);

        Cart cart = new Cart(product,customer,productQuantity);
        cartRepository.save(cart);

    }

}
