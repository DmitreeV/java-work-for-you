package pet.project.javaworkforyou.reaction.service;

import pet.project.javaworkforyou.reaction.dto.ReactionDto;

import java.util.List;

public interface ReactionService {

    ReactionDto saveReaction(Long vacancyId, Long resumeId);

    ReactionDto getReactionById(Long reactionId);

    List<ReactionDto> getAllReactionsByResumeCreator(Long resumeId, Integer from, Integer size);
    void deleteReaction(Long reactionId);
}
