package pet.project.javaworkforyou.company.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pet.project.javaworkforyou.company.model.Company;

public interface CompanyRepository extends JpaRepository<Company, Long> {
}
