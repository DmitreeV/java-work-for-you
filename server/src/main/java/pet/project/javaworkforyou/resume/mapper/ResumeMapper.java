package pet.project.javaworkforyou.resume.mapper;

import org.mapstruct.Mapper;
import pet.project.javaworkforyou.resume.dto.ResumeCreateDto;
import pet.project.javaworkforyou.resume.dto.ResumeDto;
import pet.project.javaworkforyou.resume.model.Resume;

@Mapper(componentModel = "spring")
public interface ResumeMapper {

    Resume toResume(ResumeCreateDto resumeDto);

    ResumeDto toResumeDto(Resume resume);
}
