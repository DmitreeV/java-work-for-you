package pet.project.javaworkforyou.location.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class LocationDto {

    private String name;

    private float lat;

    private float lon;

    private Long radius;
}
