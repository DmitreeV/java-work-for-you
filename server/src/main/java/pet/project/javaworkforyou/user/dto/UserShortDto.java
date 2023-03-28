package pet.project.javaworkforyou.user.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserShortDto {

    private Long id;
    private String name;
}