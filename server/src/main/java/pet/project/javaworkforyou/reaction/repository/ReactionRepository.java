package pet.project.javaworkforyou.reaction.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import pet.project.javaworkforyou.reaction.model.Reaction;
import pet.project.javaworkforyou.resume.model.Resume;

public interface ReactionRepository extends JpaRepository<Reaction, Long> {
    Reaction findByVacancyIdAndResumeId(Long vacancyId, Long resumeId);

    Page<Reaction> findAllByResume(Resume resume, Pageable pageable);
}
