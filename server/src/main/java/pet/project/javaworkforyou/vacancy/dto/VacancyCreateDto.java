package pet.project.javaworkforyou.vacancy.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import pet.project.javaworkforyou.location.dto.LocationDto;

import javax.validation.constraints.Size;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VacancyCreateDto {

    @Schema(example = "Java developer")
    private String name;

    @Size(min = 20, max = 5000)
    @Schema(example = "Vacancy description")
    private String description;

    @Schema(example = "1")
    private Long category;

    @Schema(example = "{\"lat\": 55.75, \"lon\": 65.61}")
    private LocationDto location;

    @Schema(example = "1")
    private Long company;
}
