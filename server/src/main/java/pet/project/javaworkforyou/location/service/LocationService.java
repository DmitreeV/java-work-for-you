package pet.project.javaworkforyou.location.service;

import pet.project.javaworkforyou.location.dto.LocationDto;

import java.util.List;

public interface LocationService {

    LocationDto saveLocation(LocationDto locationDto);

    LocationDto updateLocation(Long locationId, LocationDto locationDto);

    LocationDto getLocationById(Long locationId);

    List<LocationDto> getAllLocations(Integer from, Integer size);

    void deleteLocation(Long locationId);
}
