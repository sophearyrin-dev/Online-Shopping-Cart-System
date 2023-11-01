package miu.edu.cs489.shopping.onlineshoppingcart.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "orderlines")
public class OrderLine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer orderLineId;

    private int quantity;

    private double subtotal;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    public OrderLine(int quantity, double subtotal, Product product, Order order) {
        this.quantity = quantity;
        this.subtotal = subtotal;
        this.product = product;
        this.order = order;
    }
}
