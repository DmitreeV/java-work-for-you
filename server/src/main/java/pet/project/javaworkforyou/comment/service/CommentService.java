package pet.project.javaworkforyou.comment.service;

import pet.project.javaworkforyou.comment.dto.CommentCreateDto;
import pet.project.javaworkforyou.comment.dto.CommentDto;

public interface CommentService {

    CommentDto saveComment(CommentCreateDto commentDto, Long userId, Long compId);
}
