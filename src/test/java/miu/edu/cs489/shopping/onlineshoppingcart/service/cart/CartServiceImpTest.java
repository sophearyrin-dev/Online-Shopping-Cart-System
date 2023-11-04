package miu.edu.cs489.shopping.onlineshoppingcart.service.cart;

import miu.edu.cs489.shopping.onlineshoppingcart.dto.cart.CartRequest;
import miu.edu.cs489.shopping.onlineshoppingcart.dto.cart.CartResponse;
import miu.edu.cs489.shopping.onlineshoppingcart.exception.CustomerNotFoundException;
import miu.edu.cs489.shopping.onlineshoppingcart.exception.ProductNotFoundException;
import miu.edu.cs489.shopping.onlineshoppingcart.model.Cart;
import miu.edu.cs489.shopping.onlineshoppingcart.model.Category;
import miu.edu.cs489.shopping.onlineshoppingcart.model.Customer;
import miu.edu.cs489.shopping.onlineshoppingcart.model.Product;
import miu.edu.cs489.shopping.onlineshoppingcart.repository.CartRepository;
import miu.edu.cs489.shopping.onlineshoppingcart.repository.CustomerRepository;
import miu.edu.cs489.shopping.onlineshoppingcart.repository.ProductRepository;
import miu.edu.cs489.shopping.onlineshoppingcart.service.imp.CartServiceImp;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
public class CartServiceImpTest {

    @Mock
    private CartRepository cartRepository;

    @Mock
    private ProductRepository productRepository;

    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    CartServiceImp cartServiceImp;

    @Test
    public void findAllCartTest(){
        // Create a sample Cart object for testing
        Cart cart = new Cart();
        cart.setCartId(1); // Use Integer ID

        // Create sample Product and Customer objects associated with the Cart
        Product product = new Product();
        product.setProductId(100); // Use Integer ID

        // Create a Category and set it to the Product
        Category category = new Category();
        category.setCategoryId(300); // Use Integer ID
        product.setCategory(category);

        Customer customer = new Customer();
        customer.setCustomerId(200); // Use Integer ID

        cart.setProduct(product);
        cart.setCustomer(customer);

        // Mock the behavior of the cartRepository
        when(cartRepository.findAll()).thenReturn(List.of(cart));

        // Call the method to be tested
        List<CartResponse> cartResponses = cartServiceImp.findAllCart();

        // Verify that the findAll method of cartRepository was called
        verify(cartRepository, times(1)).findAll();

        // Check the results
        assertEquals(1, cartResponses.size()); // Assuming you expect one Cart in the response
        CartResponse cartResponse = cartResponses.get(0);
        assertEquals(Integer.valueOf(1), cartResponse.cartId()); // Convert to Integer

        // Verify that the Category and other properties are correctly mapped in CartResponse
        assertEquals(Integer.valueOf(300), cartResponse.product().category().categoryId()); // Convert to Integer
        // You can continue to verify other properties of CartResponse as needed

        // Verify that there are no more interactions with the mocks
        verifyNoMoreInteractions(cartRepository, productRepository, customerRepository);
    }

    @Test
    public void addToCart_ValidProductAndCustomer() throws ProductNotFoundException, CustomerNotFoundException {
        // Create a sample CartRequest with valid data
        CartRequest cartRequest = new CartRequest(1,2,3);

        // Create a sample Product and Customer
        Product product = new Product();
        product.setProductId(1); // Use Integer ID
        product.setStock(10); // Assuming there is stock available

        Customer customer = new Customer();
        customer.setCustomerId(2); // Use Integer ID

        // Mock the behavior of productRepository and customerRepository
        when(productRepository.findById(1)).thenReturn(Optional.of(product));
        when(customerRepository.findById(2)).thenReturn(Optional.of(customer));

        // Call the method to be tested
        assertDoesNotThrow(() -> cartServiceImp.addToCart(cartRequest));

        // Verify that productRepository and customerRepository findById methods were called
        verify(productRepository, times(1)).findById(1);
        verify(customerRepository, times(1)).findById(2);

        // Verify that the stock is reduced and cartRepository.save is called
        assertEquals(7, product.getStock()); // Assuming 10 - 3 = 7
        verify(cartRepository, times(1)).save(any(Cart.class));

        // Verify that there are no more interactions with the mocks
        verifyNoMoreInteractions(productRepository, customerRepository, cartRepository);
    }

    @Test
    public void addToCart_ProductNotFound() {
        // Create a sample CartRequest with a non-existent product ID
        CartRequest cartRequest = new CartRequest(1,2,3);


        // Mock the behavior of productRepository to return an empty optional (product not found)
        when(productRepository.findById(1)).thenReturn(Optional.empty());

        // Call the method to be tested and expect a ProductNotFoundException
        assertThrows(ProductNotFoundException.class, () -> cartServiceImp.addToCart(cartRequest));

        // Verify that productRepository findById method was called
        verify(productRepository, times(1)).findById(1);

        // Verify that there are no more interactions with the mocks
        verifyNoMoreInteractions(productRepository, customerRepository, cartRepository);
    }

    @Test
    public void addToCart_CustomerNotFound() {
        // Create a sample CartRequest with a non-existent customer ID
        CartRequest cartRequest = new CartRequest(1,2,3);

        // Create a sample Product
        Product product = new Product();
        product.setProductId(1); // Use Integer ID

        // Mock the behavior of customerRepository to return an empty optional (customer not found)
        when(productRepository.findById(1)).thenReturn(Optional.of(product));
        when(customerRepository.findById(2)).thenReturn(Optional.empty());

        // Call the method to be tested and expect a CustomerNotFoundException
        assertThrows(CustomerNotFoundException.class, () -> cartServiceImp.addToCart(cartRequest));

        // Verify that productRepository and customerRepository findById methods were called
        verify(productRepository, times(1)).findById(1);
        verify(customerRepository, times(1)).findById(2);

        // Verify that there are no more interactions with the mocks
        verifyNoMoreInteractions(productRepository, customerRepository, cartRepository);
    }

    @Test
    public void addToCart_ProductOutOfStock() {
        // Create a sample CartRequest with a product that is out of stock
        CartRequest cartRequest = new CartRequest(1,2,3);

        // Create a sample Product with zero stock
        Product product = new Product();
        product.setProductId(1); // Use Integer ID
        product.setStock(0);

        Customer customer = new Customer();
        customer.setCustomerId(2); // Use Integer ID

        // Mock the behavior of productRepository and customerRepository
        when(productRepository.findById(1)).thenReturn(Optional.of(product));
        when(customerRepository.findById(2)).thenReturn(Optional.of(customer));

        // Call the method to be tested and expect a ProductNotFoundException
        assertThrows(ProductNotFoundException.class, () -> cartServiceImp.addToCart(cartRequest));

        // Verify that productRepository and customerRepository findById methods were called
        verify(productRepository, times(1)).findById(1);
        verify(customerRepository, times(1)).findById(2);

        // Verify that there are no more interactions with the mocks
        verifyNoMoreInteractions(productRepository, customerRepository, cartRepository);
    }

}
