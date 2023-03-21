package pet.project.javaworkforyou.vacancy.dto;

import lombok.*;
import pet.project.javaworkforyou.location.dto.LocationDto;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VacancyCreateDto {

    private String name;

    private String description;

    private Long category;

    private LocationDto location;

    private Long company;
}
