package pet.project.javaworkforyou.company.service;

import pet.project.javaworkforyou.company.dto.CompanyCreateDto;
import pet.project.javaworkforyou.company.dto.CompanyDto;

import java.util.List;

public interface CompanyService {

    CompanyDto saveCompany(CompanyCreateDto companyCreateDto);

    void deleteCompany(Long compId);

    CompanyDto getCompanyById(Long compId);

    List<CompanyDto> getAllCompanies(Integer from, Integer size);
}
