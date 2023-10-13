package com.tags.cs451r_groupproject_backend.position.rest;

import com.tags.cs451r_groupproject_backend.position.model.Position;
import com.tags.cs451r_groupproject_backend.position.service.PositionService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/tags/api/v1")
@AllArgsConstructor

public class PositionController {
    private final PositionService positionService;
    @GetMapping("/positions")
    @ResponseStatus(HttpStatus.OK)
    public List<Position> retrieveAllPositions() {
        return positionService.findAll();
    }

    @GetMapping("/positions/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Position retrievePosition(@PathVariable Long id) {
        return positionService.findById(id);
    }

    @PostMapping("/positions")
    @ResponseStatus(HttpStatus.CREATED)
    public Position savePosition(@RequestBody Position position) {
        return positionService.savePosition(position);
    }

    @PutMapping("/positions/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Position updatePosition(@RequestBody Position position, @PathVariable Long id) {
        return positionService.updatePosition(position, id);
    }

    @DeleteMapping("/positions/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePosition(@PathVariable Long id) {
        positionService.deletePosition(id);
    }
}
