package pet.project.javaworkforyou.vacancy.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pet.project.javaworkforyou.vacancy.dto.VacancyCreateDto;
import pet.project.javaworkforyou.vacancy.dto.VacancyDto;
import pet.project.javaworkforyou.vacancy.model.Vacancy;

@Mapper(componentModel = "spring")
public interface VacancyMapper {
    @Mapping(target = "category.id", source = "category")
    @Mapping(target = "company.id", source = "company")
    Vacancy toVacancy(VacancyCreateDto vacancyCreateDto);

    VacancyDto toVacancyDto(Vacancy vacancy);
}
