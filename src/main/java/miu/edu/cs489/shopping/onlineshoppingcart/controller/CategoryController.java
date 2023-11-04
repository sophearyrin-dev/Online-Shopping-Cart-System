package miu.edu.cs489.shopping.onlineshoppingcart.controller;


import jakarta.validation.Valid;
import miu.edu.cs489.shopping.onlineshoppingcart.dto.category.CategoryRequest;
import miu.edu.cs489.shopping.onlineshoppingcart.dto.category.CategoryResponse;
import miu.edu.cs489.shopping.onlineshoppingcart.dto.category.CategoryResponseWithProduct;
import miu.edu.cs489.shopping.onlineshoppingcart.dto.product.ProductRequest;
import miu.edu.cs489.shopping.onlineshoppingcart.exception.CategoryNotFoundException;
import miu.edu.cs489.shopping.onlineshoppingcart.service.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/osc/api/v1/category")
public class CategoryController {

    private CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/list")
    public ResponseEntity<List<CategoryResponse>> getAllCategories(){
        return new ResponseEntity<>(categoryService.getAllCategories(), HttpStatus.OK);
    }

    @PostMapping("/new")
    public ResponseEntity<CategoryResponse> addNewCategory(@RequestBody @Valid CategoryRequest newAddress){
        return new ResponseEntity<>(categoryService.addNewCategory(newAddress), HttpStatus.CREATED);
    }

    @PostMapping("/{categoryId}/product")
    public ResponseEntity<CategoryResponseWithProduct> addProductToCategory(
            @PathVariable Integer categoryId,
            @RequestBody @Valid ProductRequest productRequest) throws CategoryNotFoundException {
        return new ResponseEntity<>(categoryService.addProductToCategory(categoryId,productRequest), HttpStatus.CREATED);
    }




}
