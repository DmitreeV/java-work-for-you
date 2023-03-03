package pet.project.javaworkforyou.resume.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pet.project.javaworkforyou.error.exception.ConflictException;
import pet.project.javaworkforyou.error.exception.NotFoundException;
import pet.project.javaworkforyou.resume.dto.ResumeCreateDto;
import pet.project.javaworkforyou.resume.dto.ResumeDto;
import pet.project.javaworkforyou.resume.mapper.ResumeMapper;
import pet.project.javaworkforyou.resume.model.Resume;
import pet.project.javaworkforyou.resume.repository.ResumeRepository;
import pet.project.javaworkforyou.user.model.User;
import pet.project.javaworkforyou.user.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class ResumeServiceImpl implements ResumeService {

    private final ResumeRepository resumeRepository;
    private final UserRepository userRepository;
    private final ResumeMapper resumeMapper;

    @Override
    public ResumeDto saveResume(Long userId, ResumeCreateDto resumeDto) {
        User user = getUserIfExists(userId);
        Resume resume = resumeMapper.toResume(resumeDto);

        resume.setCreator(user);
        resume.setCreatedOn(LocalDateTime.now());
        log.info("Saved new resume {}.", resumeDto);
        return resumeMapper.toResumeDto(resumeRepository.save(resume));
    }

    @Override
    public ResumeDto updateResume(Long resumeId, Long userId, ResumeCreateDto resumeDto) {
        Resume resumeToUpdate = getResume(resumeId);
        Resume resume = resumeMapper.toResume(resumeDto);

        if (!resumeToUpdate.getCreator().getId().equals(userId)) {
            throw new ConflictException("Resume data cannot be updated.");
        }
        resumeToUpdate.setDescription(resume.getDescription());
        resumeToUpdate.setCreatedOn(LocalDateTime.now());
        log.info("Updated resume with id {}.", resumeId);
        return resumeMapper.toResumeDto(resumeRepository.save(resumeToUpdate));
    }

    @Override
    @Transactional(readOnly = true)
    public ResumeDto getResumeByUserId(Long userId, Long resumeId) {
        Resume resume = getResume(resumeId);
        if (!resume.getCreator().getId().equals(userId)) {
            throw new ConflictException("Resume data cannot be updated.");
        }
        log.info("Received a resume by user with id {}.", userId);
        return resumeMapper.toResumeDto(resume);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ResumeDto> getAllResumesByUser(Long userId) {

        List<Resume> resumes = resumeRepository.findAllByCreator_Id(userId);
        log.info("Received a list of all resumes users id {} with size of {}.", userId, resumes.size());
        return resumes.stream()
                .map(resumeMapper::toResumeDto)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteResume(Long resumeId) {
        log.info("Resume with id {} is deleted.", resumeId);
        resumeRepository.deleteById(resumeId);
    }

    private User getUserIfExists(Long userId) {
        return userRepository.findById(userId).orElseThrow(() ->
                new NotFoundException(String.format("User with userId=%d not found", userId)));
    }

    private Resume getResume(Long resumeId) {
        return resumeRepository.findById(resumeId).orElseThrow(() ->
                new NotFoundException(String.format("Resume with id=%d not found", resumeId)));
    }
}
