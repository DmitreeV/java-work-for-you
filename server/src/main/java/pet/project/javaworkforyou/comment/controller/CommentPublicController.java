package pet.project.javaworkforyou.comment.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pet.project.javaworkforyou.comment.dto.CommentDto;
import pet.project.javaworkforyou.comment.service.CommentService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/comments")
@Tag(name = "Operations with comments in the public domain.")
public class CommentPublicController {

    private final CommentService commentService;

    @GetMapping("/{commentId}")
    @ResponseStatus(value = HttpStatus.OK)
    @Operation(summary = "Returns a comment by its Id.")
    public CommentDto getVacancyById(@PathVariable Long commentId) {
        return commentService.getCommentById(commentId);
    }
}
