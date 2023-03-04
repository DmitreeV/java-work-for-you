package pet.project.javaworkforyou.comment.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommentShortDto {

    private String text;
    private String authorName;
    private LocalDateTime created;
}
