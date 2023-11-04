package miu.edu.cs489.shopping.onlineshoppingcart.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.List;

/**
 * Product Model
 * @author sophearyrin
 * @version 1.0
 * @since Nov,2023
 */
//TODO: present
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int productId;

    private String SKU;

    private String description;

    @Column(nullable = false)
    @NotNull
    private double price;

    @Column(nullable = false)
    @NotNull
    private int stock;

    //TODO: image
//    private String url;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "category_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Category category;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL,
    orphanRemoval = true)
    private List<Cart> carts;




    public Product(String SKU, String description, double price, int stock) {
        this.SKU = SKU;
        this.description = description;
        this.price = price;
        this.stock = stock;
    }
}
