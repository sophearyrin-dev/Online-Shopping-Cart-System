package miu.edu.cs489.shopping.onlineshoppingcart.controller;

import jakarta.validation.Valid;
import miu.edu.cs489.shopping.onlineshoppingcart.dto.shipment.ShipmentRequest;
import miu.edu.cs489.shopping.onlineshoppingcart.dto.shipment.ShipmentResponse;
import miu.edu.cs489.shopping.onlineshoppingcart.service.ShipmentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/osc/api/v1/shipment/")
public class ShipmentController {

    private ShipmentService shipmentService;


    public ShipmentController(ShipmentService shipmentService) {
        this.shipmentService = shipmentService;
    }

    @GetMapping("/list")
    public ResponseEntity<List<ShipmentResponse>> getAllShipments(){
        return ResponseEntity.ok(shipmentService.findAllShipments());
    }


    @PostMapping("/new")
    public ResponseEntity<ShipmentResponse> addNewShipment(@RequestBody @Valid ShipmentRequest shipmentRequest){
        return new ResponseEntity<>(shipmentService.addNewShipment(shipmentRequest), HttpStatus.CREATED);
    }
}
