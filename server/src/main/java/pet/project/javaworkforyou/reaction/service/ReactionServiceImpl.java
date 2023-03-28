package pet.project.javaworkforyou.reaction.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pet.project.javaworkforyou.error.exception.ConflictException;
import pet.project.javaworkforyou.error.exception.NotFoundException;
import pet.project.javaworkforyou.reaction.dto.ReactionDto;
import pet.project.javaworkforyou.reaction.mapper.ReactionMapper;
import pet.project.javaworkforyou.reaction.model.Reaction;
import pet.project.javaworkforyou.reaction.repository.ReactionRepository;
import pet.project.javaworkforyou.resume.model.Resume;
import pet.project.javaworkforyou.resume.repository.ResumeRepository;
import pet.project.javaworkforyou.vacancy.model.State;
import pet.project.javaworkforyou.vacancy.model.Vacancy;
import pet.project.javaworkforyou.vacancy.repository.VacancyRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class ReactionServiceImpl implements ReactionService {

    private final ReactionRepository reactionRepository;
    private final VacancyRepository vacancyRepository;
    private final ResumeRepository resumeRepository;
    private final ReactionMapper reactionMapper;

    @Override
    public ReactionDto saveReaction(Long vacancyId, Long resumeId) {
        if (reactionRepository.findByVacancyIdAndResumeId(vacancyId, resumeId) != null) {
            throw new ConflictException("The reaction has already been sent, it cannot be sent again.");
        }

        Vacancy vacancy = getVacancy(vacancyId);
        Resume resume = getResume(resumeId);

        if (Objects.equals(vacancy.getRecruiter().getId(), resume.getCreator().getId())) {
            throw new ConflictException("Mistake! The creator of the resume and the creator of the vacancy is one person.");
        }

        if (!vacancy.getState().equals(State.PUBLISHED)) {
            throw new ConflictException("It is not possible to add an reaction to an unpublished vacancy.");
        }

        Reaction reaction = new Reaction();
        reaction.setCreated(LocalDateTime.now());
        reaction.setVacancy(vacancy);
        reaction.setResume(resume);

        log.info("Saved new reaction for vacancy : {}, from user with id {}.", vacancy.getName(), resume.getCreator().getId());
        return reactionMapper.toReactionDto(reactionRepository.save(reaction));
    }

    @Override
    @Transactional(readOnly = true)
    public List<ReactionDto> getAllReactionsByResumeCreator(Long resumeId, Integer from, Integer size) {
        Resume resume = getResume(resumeId);

        Page<Reaction> reactions = reactionRepository.findAllByResume(resume, PageRequest.of(from / size, size));
        log.info("Received a list of all reactions of the user id {} with size of {}.", resume.getCreator().getId(), reactions.getSize());
        return reactions.stream()
                .map(reactionMapper::toReactionDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public ReactionDto getReactionById(Long reactionId) {
        log.info("Received a reaction with id {}.", reactionId);
        return reactionMapper.toReactionDto(getReaction(reactionId));
    }

    @Override
    public void deleteReaction(Long reactionId) {
        reactionRepository.deleteById(reactionId);
        log.info("Reaction with id {} is deleted.", reactionId);
    }

    private Reaction getReaction(Long reactionId) {
        return reactionRepository.findById(reactionId).orElseThrow(() ->
                new NotFoundException(String.format("Reaction with id=%d not found", reactionId)));
    }

    private Vacancy getVacancy(Long vacancyId) {
        return vacancyRepository.findById(vacancyId).orElseThrow(() ->
                new NotFoundException(String.format("Vacancy with id=%d not found", vacancyId)));
    }

    private Resume getResume(Long resumeId) {
        return resumeRepository.findById(resumeId).orElseThrow(() ->
                new NotFoundException(String.format("Resume with id=%d not found", resumeId)));
    }
}
