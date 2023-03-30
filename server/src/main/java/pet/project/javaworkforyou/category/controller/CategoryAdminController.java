package pet.project.javaworkforyou.category.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pet.project.javaworkforyou.category.dto.CategoryDto;
import pet.project.javaworkforyou.category.dto.CategoryCreateDto;
import pet.project.javaworkforyou.category.service.CategoryService;

import javax.validation.Valid;

@RestController
@AllArgsConstructor
@RequestMapping(path = "/admin/categories")
@Tag(name = "Operations with categories available to the administrator.")
public class CategoryAdminController {

    private final CategoryService categoryService;

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    @Operation(summary = "Create a new category.")
    public CategoryDto saveCategory(@Valid @RequestBody CategoryCreateDto categoryCreateDto) {
        return categoryService.saveCategory(categoryCreateDto);
    }

    @DeleteMapping("/{catId}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    @Operation(summary = "Delete a category.")
    public void deleteCategory(@PathVariable Long catId) {
        categoryService.deleteCategory(catId);
    }

    @PatchMapping("/{catId}")
    @ResponseStatus(value = HttpStatus.OK)
    @Operation(summary = "Update a category.")
    public CategoryDto updateCategory(@PathVariable Long catId, @Valid @RequestBody CategoryCreateDto categoryCreateDto) {
        return categoryService.updateCategory(catId, categoryCreateDto);
    }
}






