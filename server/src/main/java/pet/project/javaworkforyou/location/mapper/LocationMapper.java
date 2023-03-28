package pet.project.javaworkforyou.location.mapper;

import org.mapstruct.Mapper;
import pet.project.javaworkforyou.location.dto.LocationDto;
import pet.project.javaworkforyou.location.model.Location;

@Mapper(componentModel = "spring")
public interface LocationMapper {

    Location toLocation(LocationDto locationDto);

    LocationDto toLocationDto(Location location);
}
