package pet.project.javaworkforyou.vacancy.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pet.project.javaworkforyou.vacancy.dto.VacancyDto;
import pet.project.javaworkforyou.vacancy.service.VacancyService;

import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/vacancies")
@Tag(name = "Operations with vacancies in the public domain.")
public class VacancyPublicController {

    private final VacancyService vacancyService;

    @GetMapping("/{vacancyId}")
    @Operation(summary = "Returns a vacancy by its Id.")
    public VacancyDto getVacancyById(@PathVariable Long vacancyId) {
        return vacancyService.getVacancyById(vacancyId);
    }

    @GetMapping("/search")
    @Operation(summary = "Return the list of vacancies found by text.")
    List<VacancyDto> getItemByText(@RequestParam String text,
                                   @PositiveOrZero @RequestParam(defaultValue = "0") int from,
                                   @Positive @RequestParam(defaultValue = "20") int size) {
        return vacancyService.searchVacanciesByText(text, from, size);
    }
}
