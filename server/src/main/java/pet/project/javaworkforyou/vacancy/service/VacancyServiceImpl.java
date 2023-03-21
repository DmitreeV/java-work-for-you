package pet.project.javaworkforyou.vacancy.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pet.project.javaworkforyou.category.model.Category;
import pet.project.javaworkforyou.category.repository.CategoryRepository;
import pet.project.javaworkforyou.company.model.Company;
import pet.project.javaworkforyou.company.repository.CompanyRepository;
import pet.project.javaworkforyou.error.exception.ConflictException;
import pet.project.javaworkforyou.error.exception.NotFoundException;
import pet.project.javaworkforyou.location.mapper.LocationMapper;
import pet.project.javaworkforyou.location.model.Location;
import pet.project.javaworkforyou.location.repository.LocationRepository;
import pet.project.javaworkforyou.user.model.User;
import pet.project.javaworkforyou.user.repository.UserRepository;
import pet.project.javaworkforyou.vacancy.dto.VacancyCreateDto;
import pet.project.javaworkforyou.vacancy.dto.VacancyDto;
import pet.project.javaworkforyou.vacancy.mapper.VacancyMapper;
import pet.project.javaworkforyou.vacancy.model.Vacancy;
import pet.project.javaworkforyou.vacancy.repository.VacancyRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static pet.project.javaworkforyou.vacancy.model.State.PUBLISHED;

@Service
@Slf4j
@RequiredArgsConstructor
public class VacancyServiceImpl implements VacancyService {
    private final VacancyRepository vacancyRepository;
    private final UserRepository userRepository;
    private final LocationRepository locationRepository;
    private final CategoryRepository categoryRepository;
    private final CompanyRepository companyRepository;
    private final VacancyMapper vacancyMapper;
    private final LocationMapper locationMapper;

    @Override
    public VacancyDto saveVacancy(Long recruiterId, VacancyCreateDto vacancyCreateDto) {
        User user = getUserIfExists(recruiterId);
        Vacancy vacancy = vacancyMapper.toVacancy(vacancyCreateDto);
        Location location = locationMapper.toLocation(vacancyCreateDto.getLocation());
        Category category = getCategory(vacancyCreateDto.getCategory());
        Company company = getCompany(vacancyCreateDto.getCompany());

        vacancy.setCategory(category);
        vacancy.setLocation(locationRepository.save(location));
        vacancy.setCompany(company);
        vacancy.setRecruiter(user);
        vacancy.setState(PUBLISHED);
        vacancy.setPublishedOn(LocalDateTime.now());

        log.info("Saved new vacancy : {}.", vacancyCreateDto.getName());
        return vacancyMapper.toVacancyDto(vacancyRepository.save(vacancy));
    }

    @Override
    public VacancyDto updateVacancyByRecruiter(Long vacancyId, Long userId, VacancyCreateDto vacancyCreateDto) {
        Vacancy vacancyToUpdate = getVacancy(vacancyId);
        Vacancy vacancy = vacancyMapper.toVacancy(vacancyCreateDto);

        if (!vacancyToUpdate.getRecruiter().getId().equals(userId)) {
            throw new ConflictException("Only a recruiter can change a vacancy.");
        }
        vacancyToUpdate.setName(vacancy.getName());
        vacancyToUpdate.setDescription(vacancy.getDescription());
        vacancyToUpdate.setPublishedOn(LocalDateTime.now());
        log.info("Updated vacancy with id {}.", vacancyId);

        return vacancyMapper.toVacancyDto(vacancyRepository.save(vacancyToUpdate));
    }

    @Override
    public VacancyDto getVacancyById(Long vacancyId) {
        Vacancy vacancy = getVacancy(vacancyId);
        if (!vacancy.getState().equals(PUBLISHED)) {
            throw new ConflictException("The vacancy must be published.");
        }
        long views = vacancy.getViews() + 1;
        vacancy.setViews(views);
        log.info("Received a vacancy with id {}.", vacancyId);
        return vacancyMapper.toVacancyDto(vacancy);
    }

    @Override
    public List<VacancyDto> getAllVacanciesByCompany(Long companyId, Integer from, Integer size) {
        Company company = getCompany(companyId);
        Page<Vacancy> vacancies = vacancyRepository.findAllByCompany(company, PageRequest.of(from / size, size));
        log.info("Received a list of all vacancies of the company id {} with size of {}.", companyId, vacancies.getSize());
        return vacancies.stream()
                .map(vacancyMapper::toVacancyDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<VacancyDto> searchVacanciesByText(String text, int from, int size) {

        if (text.isEmpty()) {
            return new ArrayList<>();
        }
        String query = text.toLowerCase();
        Pageable page = PageRequest.of(from / size, size);
        Page<Vacancy> vacancies = vacancyRepository.searchVacanciesByText(query, page);
        if (vacancies.isEmpty()) {
            return new ArrayList<>();
        }
        log.info("Received a list of vacancies by text search with size {}.", vacancies.getSize());
        return vacancies.stream()
                .map(vacancyMapper::toVacancyDto)
                .collect(Collectors.toList());
    }

    @Override
    public void recruiterDeleteVacancy(Long vacancyId, Long userId) {
        getUserIfExists(userId);
        Vacancy vacancy = getVacancy(vacancyId);

        if (!Objects.equals(vacancy.getRecruiter().getId(), userId)) {
            throw new ConflictException("Only the recruiter can delete a vacancy.");
        }
        vacancyRepository.deleteById(vacancyId);
        log.info("The vacancy was deleted by the user {}.", userId);
    }

    private User getUserIfExists(Long userId) {
        return userRepository.findById(userId).orElseThrow(() ->
                new NotFoundException(String.format("User with userId=%d not found", userId)));
    }

    private Company getCompany(Long compId) {
        return companyRepository.findById(compId).orElseThrow(() ->
                new NotFoundException(String.format("Company with id=%d not found", compId)));
    }

    private Vacancy getVacancy(Long vacancyId) {
        return vacancyRepository.findById(vacancyId).orElseThrow(() ->
                new NotFoundException(String.format("Vacancy with id=%d not found", vacancyId)));
    }

    private Category getCategory(Long categoryId) {
        return categoryRepository.findById(categoryId).orElseThrow(() ->
                new NotFoundException(String.format("Category with id=%d not found", categoryId)));
    }
}
