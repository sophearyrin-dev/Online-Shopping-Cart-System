package miu.edu.cs489.shopping.onlineshoppingcart.service.imp;

import miu.edu.cs489.shopping.onlineshoppingcart.dto.category.CategoryRequest;
import miu.edu.cs489.shopping.onlineshoppingcart.dto.category.CategoryResponse;
import miu.edu.cs489.shopping.onlineshoppingcart.model.Category;
import miu.edu.cs489.shopping.onlineshoppingcart.repository.CategoryRepository;
import miu.edu.cs489.shopping.onlineshoppingcart.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImp implements CategoryService {

    private CategoryRepository categoryRepository;

    public CategoryServiceImp(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
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


}

//Note: Option + Shift + Enter : add unimplement method
