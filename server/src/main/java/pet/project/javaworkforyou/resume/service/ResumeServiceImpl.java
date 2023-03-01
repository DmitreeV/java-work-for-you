package pet.project.javaworkforyou.resume.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pet.project.javaworkforyou.error.exception.NotFoundException;
import pet.project.javaworkforyou.resume.dto.ResumeDto;
import pet.project.javaworkforyou.resume.mapper.ResumeMapper;
import pet.project.javaworkforyou.resume.model.Resume;
import pet.project.javaworkforyou.resume.repository.ResumeRepository;
import pet.project.javaworkforyou.user.model.User;
import pet.project.javaworkforyou.user.repository.UserRepository;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class ResumeServiceImpl implements ResumeService {

    private final ResumeRepository resumeRepository;
    private final UserRepository userRepository;
    private final ResumeMapper resumeMapper;
    public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");


    @Override
    public ResumeDto saveResume(Long creatorId, ResumeDto resumeDto) {
        User user = getUserIfExists(creatorId);
        Resume resume = resumeMapper.toResume(resumeDto);

        resume.setCreator(user);
        resume.setCreatedOn(LocalDateTime.now());

        return resumeMapper.toResumeDto(resumeRepository.save(resume));
    }

    @Override
    public ResumeDto updateResume(ResumeDto resumeDto) {
        return null;
    }

    @Override
    public ResumeDto getResumeById(Long resumeId) {
        return null;
    }

    @Override
    public List<ResumeDto> getAllResumesByUser(List<Long> resumeIds) {
        return null;
    }

    @Override
    public void deleteResume(Long resumeId) {

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
