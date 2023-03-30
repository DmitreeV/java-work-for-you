package pet.project.javaworkforyou.vacancy.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import pet.project.javaworkforyou.location.dto.LocationDto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VacancyCreateDto {

    @Schema(example = "Java developer")
    private String name;

    @NotBlank(message = "'description' can not be blank")
    @Size(min = 20, max = 5000)
    @Schema(example = "Vacancy description")
    private String description;

    @Positive
    @Schema(example = "1")
    private Long category;

    @NotNull
    @Schema(example = "{\"lat\": 55.75, \"lon\": 65.61}")
    private LocationDto location;

    @Positive
    @Schema(example = "1")
    private Long company;
}
