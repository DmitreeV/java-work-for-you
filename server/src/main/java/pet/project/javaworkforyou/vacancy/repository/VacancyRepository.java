package pet.project.javaworkforyou.vacancy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pet.project.javaworkforyou.vacancy.model.Vacancy;

import java.util.List;

public interface VacancyRepository extends JpaRepository<Vacancy, Long> {

    List<Vacancy> findAllByCategoryId(Long categoryId);
}
