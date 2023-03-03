package pet.project.javaworkforyou.company.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CompanyShortDto {

    private Long id;
    private String name;
    private String description;
}
