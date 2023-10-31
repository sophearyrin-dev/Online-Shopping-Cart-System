package miu.edu.cs489.shopping.onlineshoppingcart.repository;

import miu.edu.cs489.shopping.onlineshoppingcart.model.CreditCard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CreditCardRepository extends JpaRepository<CreditCard, Integer> {
}
