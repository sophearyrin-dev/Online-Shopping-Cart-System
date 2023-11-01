package miu.edu.cs489.shopping.onlineshoppingcart.controller;


import miu.edu.cs489.shopping.onlineshoppingcart.dto.address.AddressResponse;
import miu.edu.cs489.shopping.onlineshoppingcart.dto.product.ProductRequest;
import miu.edu.cs489.shopping.onlineshoppingcart.dto.product.ProductResponse;
import miu.edu.cs489.shopping.onlineshoppingcart.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/osc/api/v1/product/")
public class ProductController {

    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }


    @GetMapping("/list")
    public ResponseEntity<List<ProductResponse>> getAllProducts(){
        return ResponseEntity.ok(productService.findAllProducts());
    }

    @PostMapping("/new")
    public ResponseEntity<ProductResponse> addNewProduct(
            @RequestBody ProductRequest newProduct){

        return new ResponseEntity<>(productService.addNewProduct(newProduct),
                HttpStatus.CREATED);

    }
}
