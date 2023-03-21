package pet.project.javaworkforyou.vacancy.dto;

import lombok.*;
import pet.project.javaworkforyou.category.dto.CategoryDto;
import pet.project.javaworkforyou.company.dto.CompanyDto;
import pet.project.javaworkforyou.location.dto.LocationDto;
import pet.project.javaworkforyou.user.dto.UserShortDto;
import pet.project.javaworkforyou.vacancy.model.State;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VacancyDto {

    private Long id;

    private String name;

    private String description;

    private LocalDateTime publishedOn;

    private UserShortDto recruiter;

    private CategoryDto category;

    private LocationDto location;

    private CompanyDto company;

    private State state;
}
