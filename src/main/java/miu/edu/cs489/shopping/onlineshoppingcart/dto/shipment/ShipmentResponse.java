package miu.edu.cs489.shopping.onlineshoppingcart.dto.shipment;


import miu.edu.cs489.shopping.onlineshoppingcart.dto.address.AddressResponse;
import miu.edu.cs489.shopping.onlineshoppingcart.model.Address;

import java.time.LocalDate;

public record ShipmentResponse(

        Integer shipmentId,

        LocalDate shipmentDate,

        AddressResponse address
) {
}
