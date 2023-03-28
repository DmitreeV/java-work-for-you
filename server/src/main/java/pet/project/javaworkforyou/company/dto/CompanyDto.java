package pet.project.javaworkforyou.company.dto;

import lombok.*;
import pet.project.javaworkforyou.comment.dto.CommentShortDto;
import pet.project.javaworkforyou.vacancy.dto.VacancyShortDto;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CompanyDto {

    private Long id;

    private String name;

    private String description;

    private List<CommentShortDto> comments;

    private List<VacancyShortDto> vacancies;
}
