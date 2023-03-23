package pet.project.javaworkforyou.vacancy.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pet.project.javaworkforyou.vacancy.dto.VacancyCreateDto;
import pet.project.javaworkforyou.vacancy.dto.VacancyDto;
import pet.project.javaworkforyou.vacancy.service.VacancyService;

@RestController
@AllArgsConstructor
@RequestMapping("/users/{recruiterId}/vacancies")
public class VacancyUserController {

    private final VacancyService vacancyService;

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public VacancyDto saveVacancy(@PathVariable Long recruiterId, @RequestBody VacancyCreateDto vacancyCreateDto) {
        return vacancyService.saveVacancy(recruiterId, vacancyCreateDto);
    }

    @PatchMapping("/{vacancyId}")
    @ResponseStatus(value = HttpStatus.OK)
    public VacancyDto updateEventByUserId(@PathVariable Long vacancyId, @PathVariable Long recruiterId,
                                          @RequestBody VacancyCreateDto vacancyCreateDto) {
        return vacancyService.updateVacancyByRecruiter(vacancyId, recruiterId, vacancyCreateDto);
    }

    @PatchMapping("/{vacancyId}/archive")
    @ResponseStatus(value = HttpStatus.OK)
    public VacancyDto archivedVacancyByRecruiter(@PathVariable Long vacancyId, @PathVariable Long recruiterId) {
        return vacancyService.archivedVacancyByRecruiter(vacancyId, recruiterId);
    }

    @DeleteMapping("/{vacancyId}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void userDeleteComment(@PathVariable Long vacancyId,
                                  @PathVariable Long recruiterId) {
        vacancyService.recruiterDeleteVacancy(vacancyId, recruiterId);
    }
}
