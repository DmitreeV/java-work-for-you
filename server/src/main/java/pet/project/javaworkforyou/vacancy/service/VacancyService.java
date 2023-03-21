package pet.project.javaworkforyou.vacancy.service;

import pet.project.javaworkforyou.vacancy.dto.VacancyCreateDto;
import pet.project.javaworkforyou.vacancy.dto.VacancyDto;

import java.util.List;

public interface VacancyService {

    VacancyDto saveVacancy(Long recruiterId, VacancyCreateDto vacancyCreateDto);

    VacancyDto updateVacancyByRecruiter(Long vacancyId, Long recruiterId, VacancyCreateDto vacancyCreateDto);

    VacancyDto getVacancyById(Long vacancyId);

    List<VacancyDto> getAllVacanciesByCompany(Long companyId, Integer from, Integer size);

    List<VacancyDto> searchVacanciesByText(String text, int from, int size);

    void recruiterDeleteVacancy(Long vacancyId, Long userId);
}
