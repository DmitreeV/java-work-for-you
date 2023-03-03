package pet.project.javaworkforyou.category.service;

import pet.project.javaworkforyou.category.dto.CategoryDto;
import pet.project.javaworkforyou.category.model.CategoryCreateDto;

import java.util.List;

public interface CategoryService {

    CategoryDto saveCategory(CategoryCreateDto newCategoryDto);

    CategoryDto updateCategory(Long categoryId, CategoryCreateDto categoryDto);

    CategoryDto getCategoryById(Long categoryId);

    List<CategoryDto> getAllCategories(Integer from, Integer size);

    void deleteCategory(Long categoryId);
}
