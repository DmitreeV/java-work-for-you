package pet.project.javaworkforyou.vacancy.dto;

import com.sun.istack.NotNull;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import pet.project.javaworkforyou.company.dto.CompanyDto;
import pet.project.javaworkforyou.location.dto.LocationDto;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VacancyShortDto {

    @NotBlank(message = "'name' can not be blank")
    private String name;

    @NotBlank(message = "'description' can not be blank")
    private String description;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime publishedOn;

    @NotNull
    private LocationDto location;

    @NotNull
    private CompanyDto company;
}
