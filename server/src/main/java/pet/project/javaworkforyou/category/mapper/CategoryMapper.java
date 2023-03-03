package pet.project.javaworkforyou.category.mapper;

import org.mapstruct.Mapper;
import pet.project.javaworkforyou.category.dto.CategoryDto;
import pet.project.javaworkforyou.category.model.Category;
import pet.project.javaworkforyou.category.model.CategoryCreateDto;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    Category toCategory(CategoryCreateDto categoryCreateDto);

    CategoryDto toCategoryDto(Category category);
}
