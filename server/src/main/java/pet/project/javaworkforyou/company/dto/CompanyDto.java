package pet.project.javaworkforyou.company.dto;

import lombok.*;
import pet.project.javaworkforyou.comment.dto.CommentDto;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CompanyDto {

    private Long id;

    private String name;

    private String description;

    private List<CommentDto> comments;
}
