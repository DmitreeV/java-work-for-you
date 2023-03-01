package pet.project.javaworkforyou.resume.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pet.project.javaworkforyou.resume.model.Resume;

@Repository
public interface ResumeRepository extends JpaRepository<Resume, Long> {
}
