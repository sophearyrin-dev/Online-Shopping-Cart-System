package miu.edu.cs489.shopping.onlineshoppingcart.repository;


import miu.edu.cs489.shopping.onlineshoppingcart.model.Shipment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShipmentRepository extends JpaRepository<Shipment,Integer> {

}
