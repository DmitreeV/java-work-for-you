package pet.project.javaworkforyou.reaction.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ReactionDto {

    private Long id;
    private Long vacancy;
    private Long resume;
    private String created;
}
