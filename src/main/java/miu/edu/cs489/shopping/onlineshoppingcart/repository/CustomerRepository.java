package miu.edu.cs489.shopping.onlineshoppingcart.repository;

import miu.edu.cs489.shopping.onlineshoppingcart.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer,Integer> {

}
