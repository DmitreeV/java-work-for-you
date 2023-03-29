package pet.project.javaworkforyou.company.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pet.project.javaworkforyou.comment.dto.CommentDto;
import pet.project.javaworkforyou.comment.service.CommentService;
import pet.project.javaworkforyou.company.dto.CompanyDto;
import pet.project.javaworkforyou.company.service.CompanyService;
import pet.project.javaworkforyou.vacancy.dto.VacancyDto;
import pet.project.javaworkforyou.vacancy.service.VacancyService;

import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(path = "/companies")
@Tag(name = "Operations with companies in the public domain.")
public class CompanyPublicController {

    private final CompanyService companyService;
    private final VacancyService vacancyService;
    private final CommentService commentService;

    @GetMapping
    @ResponseStatus(value = HttpStatus.OK)
    @Operation(summary = "Returns a list of all companies.")
    public List<CompanyDto> getAllCompanies(@PositiveOrZero @RequestParam(defaultValue = "0") Integer from,
                                            @Positive @RequestParam(defaultValue = "10") Integer size) {
        return companyService.getAllCompanies(from, size);
    }

    @GetMapping("/{compId}")
    @ResponseStatus(value = HttpStatus.OK)
    @Operation(summary = "Returns a company by its Id.")
    public CompanyDto getCompanyById(@PathVariable Long compId) {
        return companyService.getCompanyById(compId);
    }

    @GetMapping("/{compId}/vacancies")
    @ResponseStatus(value = HttpStatus.OK)
    @Operation(summary = "Returns a list of all company vacancies.")
    public List<VacancyDto> getAllVacanciesByCompany(@PathVariable Long compId,
                                                     @PositiveOrZero @RequestParam(defaultValue = "0") Integer from,
                                                     @Positive @RequestParam(defaultValue = "10") Integer size) {
        return vacancyService.getAllVacanciesByCompany(compId, from, size);
    }

    @GetMapping("/{compId}/comments")
    @ResponseStatus(value = HttpStatus.OK)
    @Operation(summary = "Returns a list of all company comments.")
    public List<CommentDto> getAllCommentsByCompany(@PathVariable Long compId,
                                                    @PositiveOrZero @RequestParam(defaultValue = "0") Integer from,
                                                    @Positive @RequestParam(defaultValue = "10") Integer size) {
        return commentService.getAllCommentsByCompany(compId, from, size);
    }
}
