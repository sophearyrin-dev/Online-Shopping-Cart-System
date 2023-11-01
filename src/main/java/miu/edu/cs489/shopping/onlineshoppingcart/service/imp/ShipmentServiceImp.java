package miu.edu.cs489.shopping.onlineshoppingcart.service.imp;

import miu.edu.cs489.shopping.onlineshoppingcart.dto.address.AddressResponse;
import miu.edu.cs489.shopping.onlineshoppingcart.dto.shipment.ShipmentRequest;
import miu.edu.cs489.shopping.onlineshoppingcart.dto.shipment.ShipmentResponse;
import miu.edu.cs489.shopping.onlineshoppingcart.model.Address;
import miu.edu.cs489.shopping.onlineshoppingcart.model.Shipment;
import miu.edu.cs489.shopping.onlineshoppingcart.repository.AddressRepository;
import miu.edu.cs489.shopping.onlineshoppingcart.repository.ShipmentRepository;
import miu.edu.cs489.shopping.onlineshoppingcart.service.ShipmentService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ShipmentServiceImp implements ShipmentService {

    private ShipmentRepository shipmentRepository;
    private AddressRepository addressRepository;

    public ShipmentServiceImp(ShipmentRepository shipmentRepository, AddressRepository addressRepository) {
        this.shipmentRepository = shipmentRepository;
        this.addressRepository = addressRepository;
    }

    @Override
    public List<ShipmentResponse> findAllShipments() {
        return shipmentRepository.findAll().stream()
                .map(shipment -> new ShipmentResponse(
                        shipment.getShipmentId(),
                        shipment.getShipmentDate(),
                        new AddressResponse(
                                shipment.getAddress().getAddressId(),
                                shipment.getAddress().getStreet(),
                                shipment.getAddress().getCity(),
                                shipment.getAddress().getState(),
                                shipment.getAddress().getZipCode()

                        )
                )).collect(Collectors.toList());

    }

    @Override
    public ShipmentResponse addNewShipment(ShipmentRequest shipmentRequest) {

        Optional<Address> address = addressRepository.findById(shipmentRequest.addressId());
        Shipment shipment = new Shipment(shipmentRequest.shipmentDate(), address.get());

        Shipment shipmentSave = shipmentRepository.save(shipment);

        ShipmentResponse shipmentResponse = new ShipmentResponse(
                shipmentSave.getShipmentId(),
                shipmentSave.getShipmentDate(),
                new AddressResponse(
                        shipmentSave.getAddress().getAddressId(),
                        shipmentSave.getAddress().getStreet(),
                        shipmentSave.getAddress().getCity(),
                        shipmentSave.getAddress().getState(),
                        shipmentSave.getAddress().getZipCode()

                )
        );
        return shipmentResponse;
    }
}
