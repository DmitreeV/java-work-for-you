package pet.project.javaworkforyou.category.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pet.project.javaworkforyou.category.model.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
}
