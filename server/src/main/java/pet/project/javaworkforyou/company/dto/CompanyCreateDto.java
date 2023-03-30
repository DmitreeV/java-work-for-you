package pet.project.javaworkforyou.company.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CompanyCreateDto {

    @NotBlank(message = "'name' can not be blank")
    @Schema(example = "Sbertech")
    private String name;

    @NotBlank(message = "'description' can not be blank")
    @Size(min = 10, max = 1000)
    @Schema(example = "The largest IT company in Russia")
    private String description;
}
