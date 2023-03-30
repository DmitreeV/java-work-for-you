package pet.project.javaworkforyou.user.dto;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserDto {

    private Long id;
    @NotBlank(message = "'name' can not be blank")
    private String name;
    @Email
    @NotBlank(message = "'email' can not be blank")
    private String email;
}