package miu.edu.cs489.shopping.onlineshoppingcart.service.imp;

import miu.edu.cs489.shopping.onlineshoppingcart.dto.category.CategoryRequest;
import miu.edu.cs489.shopping.onlineshoppingcart.dto.category.CategoryResponse;
import miu.edu.cs489.shopping.onlineshoppingcart.dto.category.CategoryResponseWithProduct;
import miu.edu.cs489.shopping.onlineshoppingcart.dto.product.ProductRequest;
import miu.edu.cs489.shopping.onlineshoppingcart.dto.product.ProductResponseWithoutCategory;
import miu.edu.cs489.shopping.onlineshoppingcart.exception.CategoryNotFoundException;
import miu.edu.cs489.shopping.onlineshoppingcart.model.Category;
import miu.edu.cs489.shopping.onlineshoppingcart.model.Product;
import miu.edu.cs489.shopping.onlineshoppingcart.repository.CategoryRepository;
import miu.edu.cs489.shopping.onlineshoppingcart.repository.ProductRepository;
import miu.edu.cs489.shopping.onlineshoppingcart.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImp implements CategoryService {

    private CategoryRepository categoryRepository;
    private ProductRepository productRepository;

    public CategoryServiceImp(CategoryRepository categoryRepository,
                              ProductRepository productRepository) {
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
    }

    @Override
    public List<CategoryResponse> getAllCategories() {
        return categoryRepository.findAll().stream()
                .map(c -> new CategoryResponse(
                        c.getCategoryId(),
                        c.getName()
                )).collect(Collectors.toList());
    }

    @Override
    public CategoryResponse addNewCategory(CategoryRequest newCategory) {

        Category category = new Category(newCategory.name());

        Category categorySaved = categoryRepository.save(category);

        CategoryResponse categoryResponse = new CategoryResponse(
                categorySaved.getCategoryId(),
                categorySaved.getName()
        );
        return categoryResponse;
    }

    @Override
    public CategoryResponseWithProduct addProductToCategory(
            Integer categoryId,
            ProductRequest productRequest) throws CategoryNotFoundException {

        var category = categoryRepository.findById(categoryId)
                .orElseThrow(
                        () -> (
                            new CategoryNotFoundException(String.format("Category ID: %d", categoryId, " is not found")))
                );


        Product product = new Product(
                productRequest.SKU(),
                productRequest.description(),
                productRequest.price(),
                productRequest.stock()
        );

        product.setCategory(category);

        Product productSaved = productRepository.save(product);

        CategoryResponseWithProduct categoryResponseWithProduct =
                new CategoryResponseWithProduct(
                        category.getCategoryId(),
                        category.getName(),
                        productSaved.getCategory().getProducts().stream().map(
                                p -> new ProductResponseWithoutCategory(
                                        p.getProductId(),
                                        p.getSKU(),
                                        p.getDescription(),
                                        p.getPrice(),
                                        p.getStock()
                                )
                        ).collect(Collectors.toList())

                );
        return categoryResponseWithProduct;
    }


}

//Note: Option + Shift + Enter : add unimplement method
