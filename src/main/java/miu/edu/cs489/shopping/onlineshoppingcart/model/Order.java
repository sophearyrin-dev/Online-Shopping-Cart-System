package miu.edu.cs489.shopping.onlineshoppingcart.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer orderId;

    private LocalDate orderDate;

    private double totalPrice;

    @OneToOne
    @JoinColumn(name = "shipment_id")
    private Shipment shipment;


    @OneToOne
    @JoinColumn(name = "payment_id")
    private Payment payment;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @OneToMany(mappedBy = "order")
    private List<OrderLine> orderLines;

    public Order(LocalDate orderDate, double totalPrice, Shipment shipment, Payment payment, Customer customer) {
        this.orderDate = orderDate;
        this.totalPrice = totalPrice;
        this.shipment = shipment;
        this.payment = payment;
        this.customer = customer;
    }
}
