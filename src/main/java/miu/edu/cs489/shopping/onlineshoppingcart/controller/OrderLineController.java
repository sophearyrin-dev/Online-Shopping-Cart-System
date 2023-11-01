package miu.edu.cs489.shopping.onlineshoppingcart.controller;

import jakarta.validation.Valid;
import miu.edu.cs489.shopping.onlineshoppingcart.dto.orderline.OrderLineRequest;
import miu.edu.cs489.shopping.onlineshoppingcart.dto.orderline.OrderLineResponse;
import miu.edu.cs489.shopping.onlineshoppingcart.service.OrderLineService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/osc/api/v1/orderline/")
public class OrderLineController {

    private OrderLineService orderLineService;

    public OrderLineController(OrderLineService orderLineService) {
        this.orderLineService = orderLineService;
    }

    @GetMapping("/list")
    public ResponseEntity<List<OrderLineResponse>> findAllOrderLine(){
        return ResponseEntity.ok(orderLineService.findAllOrderLine());
    }


    @PostMapping("/new")
    public ResponseEntity<OrderLineResponse> addNewOrderLine(@RequestBody @Valid OrderLineRequest orderLineRequest){
        return new ResponseEntity<>(orderLineService.addNewOrderLine(orderLineRequest), HttpStatus.CREATED);
    }

    @GetMapping("/find_by_order/{orderId}")
    public ResponseEntity<List<OrderLineResponse>> findAllOrderLineByOrderId(@PathVariable Integer orderId){
        return ResponseEntity.ok(orderLineService.findAllOrderLineByOrderId(orderId));
    }


}
