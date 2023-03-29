package pet.project.javaworkforyou.user.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserShortDto {

    private Long id;
    @NotBlank(message = "'name' can not be blank")
    private String name;
}