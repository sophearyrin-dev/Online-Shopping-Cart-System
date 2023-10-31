package miu.edu.cs489.shopping.onlineshoppingcart.repository;

import miu.edu.cs489.shopping.onlineshoppingcart.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address,Integer> {
}
