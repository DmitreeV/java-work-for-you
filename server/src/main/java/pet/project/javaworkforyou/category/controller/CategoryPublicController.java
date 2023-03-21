package pet.project.javaworkforyou.category.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pet.project.javaworkforyou.category.dto.CategoryDto;
import pet.project.javaworkforyou.category.service.CategoryService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(path = "/categories")
public class CategoryPublicController {

    private final CategoryService categoryService;

    @GetMapping
    public List<CategoryDto> getAllCategories(@RequestParam(defaultValue = "0") Integer from,
                                              @RequestParam(defaultValue = "10") Integer size) {
        return categoryService.getAllCategories(from, size);
    }

    @GetMapping("/{catId}")
    public CategoryDto getCategoryById(@PathVariable Long catId) {
        return categoryService.getCategoryById(catId);
    }
}