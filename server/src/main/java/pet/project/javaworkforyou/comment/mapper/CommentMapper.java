package pet.project.javaworkforyou.comment.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pet.project.javaworkforyou.comment.dto.CommentCreateDto;
import pet.project.javaworkforyou.comment.dto.CommentDto;
import pet.project.javaworkforyou.comment.model.Comment;

@Mapper(componentModel = "spring")
public interface CommentMapper {

    Comment toComment(CommentCreateDto commentCreateDto);
    @Mapping(source = "author.name", target = "authorName")
    CommentDto toCommentDto(Comment comment);
}
