package miu.edu.cs489.shopping.onlineshoppingcart.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "shipments")
public class Shipment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer shipmentId;

    private LocalDate shipmentDate;

    @OneToOne
    @JoinColumn(name = "address_id")
    private Address address;

    @OneToOne(mappedBy = "shipment")
    private Order order;


}
