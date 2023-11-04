package miu.edu.cs489.shopping.onlineshoppingcart.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Cart Model
 * @author sophearyrin
 * @version 1.0
 * @since Nov,2023
 */
//TODO: present
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "carts")
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int cartId;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    private int quantity;

    public Cart(Product product, Customer customer) {
        this.product = product;
        this.customer = customer;
    }

    public Cart(Product product, Customer customer, int quantity) {
        this.product = product;
        this.customer = customer;
        this.quantity = quantity;
    }
}
