package pet.project.javaworkforyou.resume.service;

import pet.project.javaworkforyou.resume.dto.ResumeDto;

import java.util.List;

public interface ResumeService {

    ResumeDto saveResume(Long creatorId, ResumeDto resumeDto);

    ResumeDto updateResume(ResumeDto resumeDto);

    ResumeDto getResumeById(Long resumeId);

    List<ResumeDto> getAllResumesByUser(List<Long> resumeIds);

    void deleteResume(Long resumeId);


}
