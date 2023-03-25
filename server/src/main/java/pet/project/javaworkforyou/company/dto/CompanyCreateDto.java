package pet.project.javaworkforyou.company.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.validation.constraints.Size;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CompanyCreateDto {

    @Schema(example = "Sbertech")
    private String name;

    @Size(min = 10, max = 1000)
    @Schema(example = "The largest IT company in Russia")
    private String description;
}
