package pet.project.javaworkforyou.vacancy.dto;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import pet.project.javaworkforyou.company.dto.CompanyDto;
import pet.project.javaworkforyou.location.dto.LocationDto;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VacancyShortDto {

    private String name;

    private String description;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime publishedOn;

    private LocationDto location;

    private CompanyDto company;
}
