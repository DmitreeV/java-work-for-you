package pet.project.javaworkforyou.resume.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pet.project.javaworkforyou.resume.dto.ResumeCreateDto;
import pet.project.javaworkforyou.resume.dto.ResumeDto;
import pet.project.javaworkforyou.resume.service.ResumeService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/users/{userId}/resumes")
public class ResumeUserController {

    private final ResumeService resumeService;

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public ResumeDto saveResume(@PathVariable Long userId, @RequestBody ResumeCreateDto resumeDto) {
        return resumeService.saveResume(userId, resumeDto);
    }

    @PatchMapping("/{resumeId}")
    @ResponseStatus(value = HttpStatus.OK)
    public ResumeDto updateResume(@PathVariable Long userId, @PathVariable Long resumeId,
                                  @RequestBody ResumeCreateDto resumeDto) {
        return resumeService.updateResume(resumeId, userId, resumeDto);
    }

    @GetMapping("/{resumeId}")
    @ResponseStatus(value = HttpStatus.OK)
    public ResumeDto getResumeByUserId(@PathVariable Long resumeId, @PathVariable Long userId) {
        return resumeService.getResumeByUserId(userId, resumeId);
    }

    @GetMapping
    @ResponseStatus(value = HttpStatus.OK)
    public List<ResumeDto> getAllResumesByUser(@PathVariable Long userId) {
        return resumeService.getAllResumesByUser(userId);
    }

    @DeleteMapping("/{resumeId}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteResume(@PathVariable Long resumeId) {
        resumeService.deleteResume(resumeId);
    }

}
