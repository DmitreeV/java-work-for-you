package pet.project.javaworkforyou.comment.service;

import pet.project.javaworkforyou.comment.dto.CommentCreateDto;
import pet.project.javaworkforyou.comment.dto.CommentDto;

import java.util.List;

public interface CommentService {

    CommentDto saveComment(CommentCreateDto commentDto, Long userId, Long compId);

    CommentDto updateComment(Long commentId, Long userId, CommentCreateDto commentDto);

    List<CommentDto> getAllCommentsByCompany(Long compId, Integer from, Integer size);

    CommentDto getCommentById(Long commentId);

    void userDeleteComment(Long commentId, Long userId);
}
