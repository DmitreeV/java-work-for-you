package pet.project.javaworkforyou.location.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pet.project.javaworkforyou.location.model.Location;

public interface LocationRepository extends JpaRepository<Location, Long> {
}
