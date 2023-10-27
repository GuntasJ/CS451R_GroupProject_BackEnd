package com.tags.cs451r_groupproject_backend.position.rest;

import com.tags.cs451r_groupproject_backend.position.dto.PositionDTO;
import com.tags.cs451r_groupproject_backend.position.model.Position;
import com.tags.cs451r_groupproject_backend.position.service.PositionService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
@RequestMapping("/tags/api/v1")
@AllArgsConstructor

public class PositionController {
    private final PositionService positionService;
    @GetMapping("/positions")
    @ResponseStatus(HttpStatus.OK)
    public List<PositionDTO> retrieveAllPositions() {
        return positionService.findAll()
                .stream()
                .map(PositionDTO::new)
                .toList();
    }

    @GetMapping("/positions/{id}")
    @ResponseStatus(HttpStatus.OK)
    public PositionDTO retrievePosition(@PathVariable Long id) {
        return new PositionDTO(positionService.findById(id));
    }

    @PostMapping("/positions")
    @ResponseStatus(HttpStatus.CREATED)
    public PositionDTO savePosition(@RequestBody Position position) {
        return new PositionDTO(positionService.savePosition(position));
    }

    @PutMapping("/positions/{id}")
    @ResponseStatus(HttpStatus.OK)
    public PositionDTO updatePosition(@RequestBody Position position, @PathVariable Long id) {
        return new PositionDTO(positionService.updatePosition(position, id));
    }

    @DeleteMapping("/positions/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePosition(@PathVariable Long id) {
        positionService.deletePosition(id);
    }
}
