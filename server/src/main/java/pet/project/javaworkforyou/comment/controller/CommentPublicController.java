package pet.project.javaworkforyou.comment.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pet.project.javaworkforyou.comment.dto.CommentDto;
import pet.project.javaworkforyou.comment.service.CommentService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/comments")
public class CommentPublicController {

    private final CommentService commentService;

    @GetMapping("/{commentId}")
    @ResponseStatus(value = HttpStatus.OK)
    public CommentDto getVacancyById(@PathVariable Long commentId) {
        return commentService.getCommentById(commentId);
    }
}
