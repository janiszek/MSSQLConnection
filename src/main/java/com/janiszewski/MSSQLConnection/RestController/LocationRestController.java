package com.janiszewski.MSSQLConnection.RestController;

import com.janiszewski.MSSQLConnection.entity.Location;
import com.janiszewski.MSSQLConnection.repository.LocationRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class LocationRestController {
    private final LocationRepository locationRepo;

    @GetMapping("/locations")
    public List<Location> locationFindAll() {
        List<Location> locationList = locationRepo.findAll();
        return locationList;
    }

}
