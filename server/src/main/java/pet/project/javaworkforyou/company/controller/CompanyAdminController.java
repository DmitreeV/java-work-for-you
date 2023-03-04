package pet.project.javaworkforyou.company.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pet.project.javaworkforyou.company.dto.CompanyCreateDto;
import pet.project.javaworkforyou.company.dto.CompanyDto;
import pet.project.javaworkforyou.company.service.CompanyService;

@RestController
@AllArgsConstructor
@RequestMapping(path = "/admin/companies")
public class CompanyAdminController {

    private final CompanyService companyService;

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public CompanyDto saveCompany(@RequestBody CompanyCreateDto companyCreateDto) {
        return companyService.saveCompany(companyCreateDto);
    }

    @DeleteMapping("/{compId}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteCompany(@PathVariable Long compId) {
        companyService.deleteCompany(compId);
    }

}
