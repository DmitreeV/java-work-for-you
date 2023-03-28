package pet.project.javaworkforyou.company.mapper;

import org.mapstruct.Mapper;
import pet.project.javaworkforyou.company.dto.CompanyCreateDto;
import pet.project.javaworkforyou.company.dto.CompanyDto;
import pet.project.javaworkforyou.company.model.Company;

import java.util.ArrayList;

@Mapper(componentModel = "spring")
public interface CompanyMapper {

    Company toCompany(CompanyCreateDto companyCreateDto);

    default CompanyDto toCompanyDto(Company company) {
        return CompanyDto.builder()
                .id(company.getId())
                .name(company.getName())
                .description(company.getDescription())
                .comments(new ArrayList<>())
                .vacancies(new ArrayList<>())
                .build();
    }
}
