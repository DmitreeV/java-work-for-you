package pet.project.javaworkforyou.company.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pet.project.javaworkforyou.company.dto.CompanyCreateDto;
import pet.project.javaworkforyou.company.dto.CompanyDto;
import pet.project.javaworkforyou.company.mapper.CompanyMapper;
import pet.project.javaworkforyou.company.model.Company;
import pet.project.javaworkforyou.company.repository.CompanyRepository;
import pet.project.javaworkforyou.error.exception.ConflictException;
import pet.project.javaworkforyou.error.exception.NotFoundException;
import pet.project.javaworkforyou.vacancy.repository.VacancyRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class CompanyServiceImpl implements CompanyService {

    private final CompanyRepository companyRepository;
    private final VacancyRepository vacancyRepository;
    private final CompanyMapper companyMapper;

    @Override
    public CompanyDto saveCompany(CompanyCreateDto companyCreateDto) {

        Company company = companyMapper.toCompany(companyCreateDto);
        try {
            companyRepository.save(company);
        } catch (DataIntegrityViolationException e) {
            throw new ConflictException("Company is already exists.");
        }
        log.info("Saved new company : {}.", companyCreateDto.getName());
        return companyMapper.toCompanyDto(company);
    }

    @Override
    public void deleteCompany(Long compId) {
        if (!vacancyRepository.findAllByCompanyId(compId).isEmpty()) {
            throw new ConflictException("It is not possible to delete a company while there are published vacancies.");
        }
        companyRepository.deleteById(compId);
        log.info("Company with id {} is deleted.", compId);
    }

    @Override
    public CompanyDto getCompanyById(Long compId) {
        log.info("Received a company with id {}.", compId);
        return companyMapper.toCompanyDto(getCompany(compId));
    }

    @Override
    public List<CompanyDto> getAllCompanies(Integer from, Integer size) {
        Pageable pageable = PageRequest.of(from, size);
        log.info("Received a list of all companies with size of {}.", size);
        return companyRepository.findAll(pageable).stream()
                .map(companyMapper::toCompanyDto)
                .collect(Collectors.toList());
    }

    private Company getCompany(Long compId) {
        return companyRepository.findById(compId).orElseThrow(() ->
                new NotFoundException(String.format("Company with id=%d not found", compId)));
    }
}
