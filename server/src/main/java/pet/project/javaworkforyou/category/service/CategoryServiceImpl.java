package pet.project.javaworkforyou.category.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pet.project.javaworkforyou.category.dto.CategoryDto;
import pet.project.javaworkforyou.category.mapper.CategoryMapper;
import pet.project.javaworkforyou.category.model.Category;
import pet.project.javaworkforyou.category.model.CategoryCreateDto;
import pet.project.javaworkforyou.category.repository.CategoryRepository;
import pet.project.javaworkforyou.error.exception.ConflictException;
import pet.project.javaworkforyou.error.exception.NotFoundException;
import pet.project.javaworkforyou.vacancy.repository.VacancyRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final VacancyRepository vacancyRepository;
    private final CategoryMapper categoryMapper;

    @Override
    public CategoryDto saveCategory(CategoryCreateDto newCategoryDto) {
        Category category = categoryMapper.toCategory(newCategoryDto);
        try {
            categoryRepository.save(category);
        } catch (DataIntegrityViolationException e) {
            throw new ConflictException("Category is already exists.");
        }
        log.info("Saved new category {}.", newCategoryDto);
        return categoryMapper.toCategoryDto(category);
    }

    @Override
    public CategoryDto updateCategory(Long categoryId, CategoryCreateDto newCategoryDto) {
        Category categoryToUpdate = getCategory(categoryId);
        Category category = categoryMapper.toCategory(newCategoryDto);

        categoryToUpdate.setName(category.getName());

        try {
            categoryRepository.save(categoryToUpdate);
        } catch (DataIntegrityViolationException e) {
            throw new ConflictException("Category is already exists.");
        }
        log.info("Updated category with id {}.", categoryId);
        return categoryMapper.toCategoryDto(categoryToUpdate);
    }

    @Override
    public CategoryDto getCategoryById(Long categoryId) {
        log.info("Received a category with id {}.", categoryId);
        return categoryMapper.toCategoryDto(getCategory(categoryId));
    }

    @Override
    public List<CategoryDto> getAllCategories(Integer from, Integer size) {
        Pageable pageable = PageRequest.of(from, size);
        log.info("Received a list of all categories with size of {}.", size);
        return categoryRepository.findAll(pageable).stream()
                .map(categoryMapper::toCategoryDto)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteCategory(Long categoryId) {
        if (!vacancyRepository.findAllByCategoryId(categoryId).isEmpty()) {
            throw new ConflictException("It is not possible to delete a category when there are vacancies in it.");
        }
        categoryRepository.deleteById(categoryId);
        log.info("Category with id {} is deleted.", categoryId);
    }

    private Category getCategory(Long categoryId) {
        return categoryRepository.findById(categoryId).orElseThrow(() ->
                new NotFoundException(String.format("Category with id=%d not found", categoryId)));
    }

}