package pet.project.javaworkforyou.resume.dto;

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
    private String createdOn;
    private UserShortDto creator;
}
