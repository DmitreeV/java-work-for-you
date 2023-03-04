package pet.project.javaworkforyou.comment.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CommentDto {

    private Long id;
    private String text;
    private String authorName;
    private LocalDateTime created;
}
