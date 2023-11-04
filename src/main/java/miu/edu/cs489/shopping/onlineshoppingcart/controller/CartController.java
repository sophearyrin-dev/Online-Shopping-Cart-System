package miu.edu.cs489.shopping.onlineshoppingcart.controller;

import miu.edu.cs489.shopping.onlineshoppingcart.dto.cart.CartRequest;
import miu.edu.cs489.shopping.onlineshoppingcart.dto.cart.CartResponse;
import miu.edu.cs489.shopping.onlineshoppingcart.exception.CustomerNotFoundException;
import miu.edu.cs489.shopping.onlineshoppingcart.exception.ProductNotFoundException;
import miu.edu.cs489.shopping.onlineshoppingcart.service.CartService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * Cart Rest Controller
 * @author sophearyrin
 * @version 1.0
 * @since Nov,2023
 */
@RestController
@RequestMapping("/osc/api/v1/cart")
public class CartController {

    private CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping("/list")
    public ResponseEntity<List<CartResponse>> getAllCarts(){
        return ResponseEntity.ok(cartService.findAllCart());
    }

    @PostMapping("/add")
    public ResponseEntity<String> addToCart(@RequestBody CartRequest cartRequest) throws ProductNotFoundException, CustomerNotFoundException {
            cartService.addToCart(cartRequest);
            return ResponseEntity.ok("Product added to the cart successfully.");
    }

}
