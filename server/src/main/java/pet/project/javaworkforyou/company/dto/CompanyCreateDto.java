package pet.project.javaworkforyou.company.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CompanyCreateDto {

    private String name;
    private String description;
}
