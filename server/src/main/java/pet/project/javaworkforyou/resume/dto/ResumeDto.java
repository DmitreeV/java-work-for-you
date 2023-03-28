package pet.project.javaworkforyou.resume.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import pet.project.javaworkforyou.user.dto.UserShortDto;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ResumeDto {

    private Long id;

    private String description;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private String createdOn;

    private UserShortDto creator;
}
