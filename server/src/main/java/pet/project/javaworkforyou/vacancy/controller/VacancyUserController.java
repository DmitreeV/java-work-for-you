package pet.project.javaworkforyou.vacancy.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pet.project.javaworkforyou.vacancy.dto.VacancyCreateDto;
import pet.project.javaworkforyou.vacancy.dto.VacancyDto;
import pet.project.javaworkforyou.vacancy.service.VacancyService;

@RestController
@AllArgsConstructor
@RequestMapping("/users/{recruiterId}/vacancies")
@Tag(name = "Operations with vacancies available to the authorized user.")
public class VacancyUserController {

    private final VacancyService vacancyService;

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    @Operation(summary = "Create a new vacancy by user.")
    public VacancyDto saveVacancy(@PathVariable Long recruiterId, @RequestBody VacancyCreateDto vacancyCreateDto) {
        return vacancyService.saveVacancy(recruiterId, vacancyCreateDto);
    }

    @PatchMapping("/{vacancyId}")
    @ResponseStatus(value = HttpStatus.OK)
    @Operation(summary = "Update a vacancy by user.")
    public VacancyDto updateVacancyByRecruiter(@PathVariable Long vacancyId, @PathVariable Long recruiterId,
                                          @RequestBody VacancyCreateDto vacancyCreateDto) {
        return vacancyService.updateVacancyByRecruiter(vacancyId, recruiterId, vacancyCreateDto);
    }

    @PatchMapping("/{vacancyId}/archive")
    @ResponseStatus(value = HttpStatus.OK)
    @Operation(summary = "Move a vacancy to the archive.")
    public VacancyDto archivedVacancyByRecruiter(@PathVariable Long vacancyId, @PathVariable Long recruiterId) {
        return vacancyService.archivedVacancyByRecruiter(vacancyId, recruiterId);
    }

    @DeleteMapping("/{vacancyId}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    @Operation(summary = "Delete a vacancy by user.")
    public void userDeleteComment(@PathVariable Long vacancyId,
                                  @PathVariable Long recruiterId) {
        vacancyService.recruiterDeleteVacancy(vacancyId, recruiterId);
    }
}
