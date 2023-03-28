package pet.project.javaworkforyou.comment.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pet.project.javaworkforyou.comment.dto.CommentCreateDto;
import pet.project.javaworkforyou.comment.dto.CommentDto;
import pet.project.javaworkforyou.comment.service.CommentService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users/{userId}/comments")
@Tag(name = "Operations with comments available to the authorized user.")
public class CommentUserController {

    private final CommentService commentService;

    @PostMapping("/{compId}")
    @ResponseStatus(value = HttpStatus.CREATED)
    @Operation(summary = "Create a new comment by user.")
    public CommentDto saveComment(@PathVariable Long userId, @PathVariable Long compId, @RequestBody CommentCreateDto commentDto) {
        return commentService.saveComment(commentDto, userId, compId);
    }

    @PatchMapping("/{commentId}")
    @ResponseStatus(value = HttpStatus.OK)
    @Operation(summary = "Update a comment by user.")
    public CommentDto updateComment(@PathVariable Long commentId, @PathVariable Long userId,
                                    @RequestBody CommentCreateDto commentDto) {
        return commentService.updateComment(commentId, userId, commentDto);
    }

    @DeleteMapping("/{commentId}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    @Operation(summary = "Delete a comment by user.")
    public void userDeleteComment(@PathVariable Long userId,
                                  @PathVariable Long commentId) {
        commentService.userDeleteComment(commentId, userId);
    }
}
