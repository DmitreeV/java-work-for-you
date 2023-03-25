package pet.project.javaworkforyou.reaction.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pet.project.javaworkforyou.reaction.dto.ReactionDto;
import pet.project.javaworkforyou.reaction.service.ReactionService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/users/reactions")
@Tag(name = "Operations with reactions available to the authorized user.")
public class ReactionUserController {

    private final ReactionService reactionService;

    @PostMapping("/{resumeId}/{vacancyId}")
    @Operation(summary = "Create a new reaction by user.")
    @ResponseStatus(value = HttpStatus.CREATED)
    public ReactionDto saveReaction(@PathVariable Long vacancyId, @PathVariable Long resumeId) {
        return reactionService.saveReaction(vacancyId, resumeId);
    }

    @GetMapping("/{resumeId}/all")
    @ResponseStatus(value = HttpStatus.OK)
    @Operation(summary = "Returns all user reactions.")
    public List<ReactionDto> getAllReactionsByResumeCreator(@PathVariable Long resumeId,
                                                            @RequestParam(defaultValue = "0") Integer from,
                                                            @RequestParam(defaultValue = "10") Integer size) {
        return reactionService.getAllReactionsByResumeCreator(resumeId, from, size);
    }

    @GetMapping("/{reactionId}")
    @ResponseStatus(value = HttpStatus.OK)
    @Operation(summary = "Returns a reaction by its Id.")
    public ReactionDto getResumeByUserId(@PathVariable Long reactionId) {
        return reactionService.getReactionById(reactionId);
    }

    @DeleteMapping("/{reactionId}")
    @Operation(summary = "Delete a new reaction by user.")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteReaction(@PathVariable Long reactionId) {
        reactionService.deleteReaction(reactionId);
    }
}
