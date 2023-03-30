package pet.project.javaworkforyou.resume.dto;

import com.sun.istack.NotNull;
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
public class ResumeCreateDto {

    @NotBlank(message = "'description' can not be blank")
    @Size(min = 20, max = 5000)
    @Schema(example = "Resume description")
    private String description;
}
