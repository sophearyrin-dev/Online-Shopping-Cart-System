package miu.edu.cs489.shopping.onlineshoppingcart.service;

import miu.edu.cs489.shopping.onlineshoppingcart.dto.cart.CartRequest;
import miu.edu.cs489.shopping.onlineshoppingcart.dto.cart.CartResponse;

import java.util.List;

public interface CartService {

    List<CartResponse> findAllCart();

    CartResponse addProductToCart(CartRequest cartRequest);
}
