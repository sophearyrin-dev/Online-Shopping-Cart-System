package miu.edu.cs489.shopping.onlineshoppingcart.dto.shipment;

import java.time.LocalDate;

public record ShipmentRequest(
        LocalDate shipmentDate,
        Integer addressId
) {


}
