package pet.project.javaworkforyou.category.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CategoryCreateDto {

    @Schema(example = "Construction")
    private String name;
}
