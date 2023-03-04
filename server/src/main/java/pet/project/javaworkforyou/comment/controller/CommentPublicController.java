package pet.project.javaworkforyou.comment.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pet.project.javaworkforyou.comment.dto.CommentCreateDto;
import pet.project.javaworkforyou.comment.dto.CommentDto;
import pet.project.javaworkforyou.comment.service.CommentService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users/{userId}/comments")
public class CommentPublicController {

    private final CommentService commentService;

    @PostMapping("/{compId}")
    @ResponseStatus(value = HttpStatus.CREATED)
    public CommentDto saveComment(@PathVariable Long userId, @PathVariable Long compId, @RequestBody CommentCreateDto commentDto) {
        return commentService.saveComment(commentDto, userId, compId);
    }
}
