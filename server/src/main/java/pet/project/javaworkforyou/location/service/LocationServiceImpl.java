package pet.project.javaworkforyou.location.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pet.project.javaworkforyou.error.exception.ConflictException;
import pet.project.javaworkforyou.error.exception.NotFoundException;
import pet.project.javaworkforyou.location.dto.LocationDto;
import pet.project.javaworkforyou.location.mapper.LocationMapper;
import pet.project.javaworkforyou.location.model.Location;
import pet.project.javaworkforyou.location.repository.LocationRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class LocationServiceImpl implements LocationService {

    private final LocationRepository locationRepository;
    private final LocationMapper locationMapper;

    @Override
    public LocationDto saveLocation(LocationDto locationDto) {
        validateLocation(locationDto.getLat(), locationDto.getLon(), locationDto.getRadius());
        Location location = locationMapper.toLocation(locationDto);
        try {
            locationRepository.save(location);
        } catch (DataIntegrityViolationException e) {
            throw new ConflictException("Location is already exists.");
        }
        log.info("Saved new location {}.", locationDto);
        return locationMapper.toLocationDto(location);
    }

    @Override
    public LocationDto updateLocation(Long locationId, LocationDto locationDto) {
        validateLocation(locationDto.getLat(), locationDto.getLon(), locationDto.getRadius());
        Location locationToUpdate = getLocation(locationId);
        Location location = locationMapper.toLocation(locationDto);

        locationToUpdate.setName(location.getName());
        locationToUpdate.setLat(location.getLat());
        locationToUpdate.setLon(location.getLon());
        locationToUpdate.setRadius(location.getRadius());

        log.info("Updated location with id {}.", locationId);
        return locationMapper.toLocationDto(locationRepository.save(locationToUpdate));
    }

    @Override
    @Transactional(readOnly = true)
    public LocationDto getLocationById(Long locationId) {
        log.info("Received a location with id {}.", locationId);
        return locationMapper.toLocationDto(getLocation(locationId));
    }

    @Override
    @Transactional(readOnly = true)
    public List<LocationDto> getAllLocations(Integer from, Integer size) {
        Pageable pageable = PageRequest.of(from, size);
        log.info("Received a list of all locations with size of {}.", size);
        return locationRepository.findAll(pageable).stream()
                .map(locationMapper::toLocationDto)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteLocation(Long locationId) {
        locationRepository.deleteById(locationId);
        log.info("Location with id {} is deleted.", locationId);
    }

    private void validateLocation(float lat, float lon, Long radius) {
        if (lat == 0) {
            throw new ConflictException("The latitude of the location is not specified.");
        }
        if (lat < -90 || lat > 90) {
            throw new ConflictException("The latitude value should be in the range [-90, 90] degrees.");
        }

        if (lon == 0) {
            throw new ConflictException("The longitude of the location is not specified.");
        }
        if (lon < 0 || lon > 180) {
            throw new ConflictException("The longitude value should be in the range [0, 180] degrees.");
        }

        if (radius == null) {
            throw new ConflictException("The radius of the location is not specified.");
        }
        if (radius < 0 || radius > 100) {
            throw new ConflictException("The radius value should be in the range [0, 100] kilometers");
        }
    }

    private Location getLocation(Long locationId) {
        return locationRepository.findById(locationId).orElseThrow(() ->
                new NotFoundException(String.format("Location with locationId=%d not found", locationId)));
    }
}
