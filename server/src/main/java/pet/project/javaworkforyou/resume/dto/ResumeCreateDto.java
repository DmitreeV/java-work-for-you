package pet.project.javaworkforyou.resume.dto;

import com.sun.istack.NotNull;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ResumeCreateDto {

    @NotNull
    private String description;
}
