package miu.edu.cs489.shopping.onlineshoppingcart.service;

import miu.edu.cs489.shopping.onlineshoppingcart.dto.shipment.ShipmentRequest;
import miu.edu.cs489.shopping.onlineshoppingcart.dto.shipment.ShipmentResponse;

import java.util.List;

public interface ShipmentService {

    List<ShipmentResponse> findAllShipments();

    ShipmentResponse addNewShipment(ShipmentRequest shipmentRequest);

}
