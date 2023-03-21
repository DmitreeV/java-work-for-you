package pet.project.javaworkforyou.vacancy.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pet.project.javaworkforyou.vacancy.dto.VacancyDto;
import pet.project.javaworkforyou.vacancy.service.VacancyService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/vacancies")
public class VacancyPublicController {

    private final VacancyService vacancyService;

    @GetMapping("/{vacancyId}")
    public VacancyDto getVacancyById(@PathVariable Long vacancyId) {
        return vacancyService.getVacancyById(vacancyId);
    }

    @GetMapping("/search")
    List<VacancyDto> getItemByText(@RequestParam String text,
                                   @RequestParam(defaultValue = "0") int from,
                                   @RequestParam(defaultValue = "20") int size) {
        return vacancyService.searchVacanciesByText(text, from, size);
    }
}
