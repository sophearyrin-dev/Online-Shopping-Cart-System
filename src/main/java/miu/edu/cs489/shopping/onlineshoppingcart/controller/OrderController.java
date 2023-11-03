package miu.edu.cs489.shopping.onlineshoppingcart.controller;

import jakarta.validation.Valid;
import miu.edu.cs489.shopping.onlineshoppingcart.dto.address.AddressRequest;
import miu.edu.cs489.shopping.onlineshoppingcart.dto.address.AddressResponse;
import miu.edu.cs489.shopping.onlineshoppingcart.dto.order.OrderResponse;
import miu.edu.cs489.shopping.onlineshoppingcart.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/osc/api/v1/order/")
public class OrderController {

    private OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/list")
    public ResponseEntity<List<OrderResponse>> findAllOrder(){
        return ResponseEntity.ok(orderService.findAllOrder());
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<OrderResponse> getOrderById(@PathVariable Integer orderId){
        return new ResponseEntity<>(orderService.getOrderByOrderId(orderId), HttpStatus.FOUND);
    }


//    @PostMapping("/new")
//    public ResponseEntity<OrderResponse> addNewAddress(@RequestBody @Valid AddressRequest addressRequest){
//        return new ResponseEntity<>(addressService.addNewAddress(addressRequest), HttpStatus.CREATED);
//    }
}
