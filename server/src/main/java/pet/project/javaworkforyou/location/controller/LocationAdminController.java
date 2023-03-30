package pet.project.javaworkforyou.location.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pet.project.javaworkforyou.location.dto.LocationDto;
import pet.project.javaworkforyou.location.service.LocationService;

import javax.validation.Valid;

@RestController
@AllArgsConstructor
@RequestMapping(path = "/admin/locations")
@Tag(name = "Operations with locations available to the administrator.")
public class LocationAdminController {

    private final LocationService locationService;

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    @Operation(summary = "Create a new location.")
    public LocationDto saveLocation(@Valid @RequestBody LocationDto locationDto) {
        return locationService.saveLocation(locationDto);
    }

    @PatchMapping("/{locationId}")
    @ResponseStatus(value = HttpStatus.OK)
    @Operation(summary = "Update a location.")
    public LocationDto updateLocation(@PathVariable Long locationId, @Valid @RequestBody LocationDto locationDto) {
        return locationService.updateLocation(locationId, locationDto);
    }

    @DeleteMapping("/{locationId}")
    @Operation(summary = "Delete a location.")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteLocation(@PathVariable Long locationId) {
        locationService.deleteLocation(locationId);
    }
}
