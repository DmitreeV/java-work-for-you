package pet.project.javaworkforyou.comment.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pet.project.javaworkforyou.comment.dto.CommentCreateDto;
import pet.project.javaworkforyou.comment.dto.CommentDto;
import pet.project.javaworkforyou.comment.mapper.CommentMapper;
import pet.project.javaworkforyou.comment.model.Comment;
import pet.project.javaworkforyou.comment.repository.CommentRepository;
import pet.project.javaworkforyou.company.model.Company;
import pet.project.javaworkforyou.company.repository.CompanyRepository;
import pet.project.javaworkforyou.error.exception.ConflictException;
import pet.project.javaworkforyou.error.exception.NotFoundException;
import pet.project.javaworkforyou.user.model.User;
import pet.project.javaworkforyou.user.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.Objects;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final CompanyRepository companyRepository;
    private final CommentMapper commentMapper;

    @Override
    public CommentDto saveComment(CommentCreateDto commentDto, Long userId, Long compId) {
        User user = getUserIfExists(userId);
        Company company = getCompanyIfExists(compId);
        Comment comment = commentMapper.toComment(commentDto);

        comment.setAuthor(user);
        comment.setCompany(company);
        comment.setCreated(LocalDateTime.now());
        log.info("Saved new comment : < {} > to the company : {}", commentDto.getText(), company.getName());
        return commentMapper.toCommentDto(commentRepository.save(comment));
    }

    @Override
    public void userDeleteComment(Long commentId, Long userId) {
        getUserIfExists(userId);
        Comment comment = getComment(commentId);

        if (!Objects.equals(comment.getAuthor().getId(), userId)) {
            throw new ConflictException("Only the author can delete a comment.");
        }
        commentRepository.deleteById(commentId);
        log.info("The comment was deleted by the user {}.", userId);
    }

    private User getUserIfExists(Long userId) {
        return userRepository.findById(userId).orElseThrow(() ->
                new NotFoundException(String.format("User with userId=%d not found", userId)));
    }

    private Company getCompanyIfExists(Long compId) {
        return companyRepository.findById(compId).orElseThrow(() ->
                new NotFoundException(String.format("Company with id=%d not found", compId)));
    }

    private Comment getComment(Long commentId) {
        return commentRepository.findById(commentId).orElseThrow(() ->
                new NotFoundException(String.format("Comment with commentId=%d not found", commentId)));
    }
}
