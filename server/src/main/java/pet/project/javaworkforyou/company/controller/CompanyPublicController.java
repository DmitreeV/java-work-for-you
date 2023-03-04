package pet.project.javaworkforyou.company.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pet.project.javaworkforyou.company.dto.CompanyDto;
import pet.project.javaworkforyou.company.service.CompanyService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(path = "/companies")
public class CompanyPublicController {

    private final CompanyService companyService;

    @GetMapping
    public List<CompanyDto> getAllCompanies(@RequestParam(defaultValue = "0") Integer from,
                                            @RequestParam(defaultValue = "10") Integer size) {
        return companyService.getAllCompanies(from, size);
    }

    @GetMapping("/{compId}")
    public CompanyDto getCompanyById(@PathVariable Long compId) {
        return companyService.getCompanyById(compId);
    }
}
