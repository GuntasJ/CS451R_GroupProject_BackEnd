package com.tags.cs451r_groupproject_backend.position.service;

import com.tags.cs451r_groupproject_backend.application.model.Application;
import com.tags.cs451r_groupproject_backend.application.repository.ApplicationRepository;
import com.tags.cs451r_groupproject_backend.position.model.Note;
import com.tags.cs451r_groupproject_backend.position.model.Position;
import com.tags.cs451r_groupproject_backend.position.model.PositionId;
import com.tags.cs451r_groupproject_backend.position.respository.PositionRepository;
import com.tags.cs451r_groupproject_backend.position.rest.InvalidPositionException;
import com.tags.cs451r_groupproject_backend.position.rest.PositionAlreadyExistsException;
import com.tags.cs451r_groupproject_backend.position.rest.PositionNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PositionService {
    private PositionRepository positionRepository;
    private ApplicationRepository applicationRepository;

    public List<Position> findAll() {
        return positionRepository.findAll();
    }

    public Position findById(PositionId id) {
        Optional<Position> positionOptional = positionRepository.findById(id);
        return positionOptional.orElseThrow(() -> new PositionNotFoundException(id));
    }

    private boolean isApplicationEligibleForPosition(Application application, Position position) {
        return application.getDesiredClasses().contains(position.getPositionClass()) &&
                application.getStanding().equals(position.getRequiredStanding());
    }
    public Position createPosition(Position position) {
        //Check if the Position is valid
        if(position.getPositionClass() == null || position.getPositionClass().equals("")) {
            throw new InvalidPositionException();
        }

        if(position.getRequiredStanding() == null || position.getRequiredStanding().equals("")) {
            throw new InvalidPositionException();
        }

        if(position.getSemesters().isEmpty()) {
            throw new InvalidPositionException();
        }


        //Throw exception if position already exists
        PositionId id = new PositionId(position.getPositionClass(), position.getPositionType());
        if(positionRepository.findById(id).isPresent()) {
            throw new PositionAlreadyExistsException(id);
        }
        //When saving a position, check if there are applicants that have desiredClasses that match up with that position
        //If so, add them to the position
        //Don't add them if they do not meet the required standing
        applicationRepository.findAll()
                .stream()
                .filter(application -> isApplicationEligibleForPosition(application, position))
                .forEach(position::addApplication);

        return positionRepository.save(position);
    }

    public Position updatePosition(Position newPosition, PositionId id) {
        return positionRepository.findById(id).map(position -> {
            position.copyFrom(newPosition);
            return positionRepository.save(position);
        }).orElseThrow(() -> new PositionNotFoundException(id));
    }

    public Position updatePositionNotes(Note note, PositionId id) {
        return positionRepository.findById(id).map(position -> {
            position.setNotes(note.getNotes());
            return positionRepository.save(position);
        }).orElseThrow(() -> new PositionNotFoundException(id));
    }

    public void deletePosition(PositionId id) {
        Optional<Position> positionOptional = positionRepository.findById(id);
        if(positionOptional.isPresent()) {
            Position position = positionOptional.get();
            while(!position.getApplicants().isEmpty()) {
                position.removeApplication(position.getApplicants().get(0));
            }
            positionRepository.delete(positionOptional.get());
        }
        else {
            throw new PositionNotFoundException(id);
        }
    }
}
