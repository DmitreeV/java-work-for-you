package pet.project.javaworkforyou.resume.service;

import pet.project.javaworkforyou.resume.dto.ResumeCreateDto;
import pet.project.javaworkforyou.resume.dto.ResumeDto;

import java.util.List;

public interface ResumeService {

    ResumeDto saveResume(Long userId, ResumeCreateDto resumeDto);

    ResumeDto updateResume(Long resumeId, Long userId, ResumeCreateDto resumeDto);

    ResumeDto getResumeByUserId(Long userId, Long resumeId);

    List<ResumeDto> getAllResumesByUser(Long userId);

    void deleteResume(Long resumeId);
}
