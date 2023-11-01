package miu.edu.cs489.shopping.onlineshoppingcart.controller;


import jakarta.validation.Valid;
import miu.edu.cs489.shopping.onlineshoppingcart.dto.cart.CartRequest;
import miu.edu.cs489.shopping.onlineshoppingcart.dto.cart.CartResponse;
import miu.edu.cs489.shopping.onlineshoppingcart.service.CartService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

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


    @PostMapping("/new")
    public ResponseEntity<CartResponse> addNewAddress(@RequestBody @Valid CartRequest cartRequest){
        return new ResponseEntity<>(cartService.addProductToCart(cartRequest), HttpStatus.CREATED);
    }
}
