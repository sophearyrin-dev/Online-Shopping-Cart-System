package miu.edu.cs489.shopping.onlineshoppingcart.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

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

    private double price;

    private int stock;

    //TODO: image
//    private String url;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @OneToMany(mappedBy = "product")
    private List<Wishlist> wishlists;

    @OneToMany(mappedBy = "product")
    private List<Cart> carts;


    @OneToMany(mappedBy = "product")
    private List<OrderLine> orderLines;

    public Product(String SKU, String description, double price, int stock) {
        this.SKU = SKU;
        this.description = description;
        this.price = price;
        this.stock = stock;
    }
}
