package pet.project.javaworkforyou.location.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pet.project.javaworkforyou.location.dto.LocationDto;
import pet.project.javaworkforyou.location.service.LocationService;
import pet.project.javaworkforyou.vacancy.dto.VacancyDto;
import pet.project.javaworkforyou.vacancy.service.VacancyService;

import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(path = "/locations")
@Tag(name = "Operations with locations in the public domain.")
public class LocationPublicController {

    private final LocationService locationService;
    private final VacancyService vacancyService;

    @GetMapping
    @ResponseStatus(value = HttpStatus.OK)
    @Operation(summary = "Returns a list of all locations.")
    public List<LocationDto> getAllLocations(@PositiveOrZero @RequestParam(defaultValue = "0") Integer from,
                                             @Positive @RequestParam(defaultValue = "10") Integer size) {
        return locationService.getAllLocations(from, size);
    }

    @GetMapping("/{locationId}")
    @ResponseStatus(value = HttpStatus.OK)
    @Operation(summary = "Returns a location by its Id.")
    public LocationDto getLocationById(@PathVariable Long locationId) {
        return locationService.getLocationById(locationId);
    }

    @GetMapping("/{locationId}/vacancies")
    @ResponseStatus(value = HttpStatus.OK)
    @Operation(summary = "Returns a list of all vacancies in location.")
    public List<VacancyDto> getAllVacanciesByLocation(@PathVariable Long locationId,
                                                      @PositiveOrZero @RequestParam(defaultValue = "0") Integer from,
                                                      @Positive @RequestParam(defaultValue = "10") Integer size) {
        return vacancyService.getAllVacanciesInLocation(locationId, from, size);
    }
}
