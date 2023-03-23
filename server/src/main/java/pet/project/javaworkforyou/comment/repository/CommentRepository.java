package pet.project.javaworkforyou.comment.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import pet.project.javaworkforyou.comment.model.Comment;
import pet.project.javaworkforyou.company.model.Company;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    Optional<Comment> findByIdAndAuthorId(Long id, Long userId);

    List<Comment> findAllByCompany(Company company, Pageable pageable);
}
