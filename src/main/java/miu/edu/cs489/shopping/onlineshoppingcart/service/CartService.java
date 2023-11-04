package miu.edu.cs489.shopping.onlineshoppingcart.service;

import miu.edu.cs489.shopping.onlineshoppingcart.dto.cart.CartRequest;
import miu.edu.cs489.shopping.onlineshoppingcart.dto.cart.CartResponse;
import miu.edu.cs489.shopping.onlineshoppingcart.exception.CustomerNotFoundException;
import miu.edu.cs489.shopping.onlineshoppingcart.exception.ProductNotFoundException;

import java.util.List;
public interface CartService {

    List<CartResponse> findAllCart();

    void addToCart(CartRequest cartRequest) throws ProductNotFoundException, CustomerNotFoundException;
}
