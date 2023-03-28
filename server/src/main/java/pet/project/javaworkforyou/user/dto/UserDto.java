package pet.project.javaworkforyou.user.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserDto {

    private Long id;
    private String name;
    private String email;
}