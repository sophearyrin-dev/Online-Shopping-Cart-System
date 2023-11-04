package miu.edu.cs489.shopping.onlineshoppingcart.repository;

import miu.edu.cs489.shopping.onlineshoppingcart.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product,Integer> {

    List<Product> findProductBySKUContainingOrDescriptionContainingOrCategory_Name(
            String SKU, String description,String name
    );
}
