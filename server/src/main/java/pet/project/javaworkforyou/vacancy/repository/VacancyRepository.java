package pet.project.javaworkforyou.vacancy.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pet.project.javaworkforyou.company.model.Company;
import pet.project.javaworkforyou.vacancy.model.Vacancy;

import java.util.List;
import java.util.Optional;

public interface VacancyRepository extends JpaRepository<Vacancy, Long> {

    List<Vacancy> findAllByCategoryId(Long categoryId);

    List<Vacancy> findAllByCompanyId(Long companyId);

    @Query(value = "SELECT * FROM vacancies " +
            "WHERE (LOWER(name) LIKE '%' || ?1 || '%')",
            nativeQuery = true)
    Page<Vacancy> searchVacanciesByText(String query, Pageable page);

    Page<Vacancy> findAllByCompany(Company company, Pageable pageable);

    Optional<Vacancy> findByIdAndRecruiterId(Long vacancyId, Long userId);
}
