package pet.project.javaworkforyou.company.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pet.project.javaworkforyou.company.dto.CompanyCreateDto;
import pet.project.javaworkforyou.company.dto.CompanyDto;
import pet.project.javaworkforyou.company.service.CompanyService;

import javax.validation.Valid;

@RestController
@AllArgsConstructor
@RequestMapping(path = "/admin/companies")
@Tag(name = "Operations with companies available to the administrator.")
public class CompanyAdminController {

    private final CompanyService companyService;

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    @Operation(summary = "Create a new company.")
    public CompanyDto saveCompany(@Valid @RequestBody CompanyCreateDto companyCreateDto) {
        return companyService.saveCompany(companyCreateDto);
    }

    @DeleteMapping("/{compId}")
    @Operation(summary = "Delete a company.")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteCompany(@PathVariable Long compId) {
        companyService.deleteCompany(compId);
    }

}
