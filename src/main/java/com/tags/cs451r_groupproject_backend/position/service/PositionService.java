package com.tags.cs451r_groupproject_backend.position.service;

import com.tags.cs451r_groupproject_backend.position.model.Position;
import com.tags.cs451r_groupproject_backend.position.respository.PositionRepository;
import com.tags.cs451r_groupproject_backend.position.rest.PositionAlreadyExistsException;
import com.tags.cs451r_groupproject_backend.position.rest.PositionNotFoundException;
import com.tags.cs451r_groupproject_backend.student.model.Student;
import com.tags.cs451r_groupproject_backend.student.repository.StudentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PositionService {
    private PositionRepository positionRepository;
    private StudentRepository studentRepository;

    public List<Position> findAll() {
        return positionRepository.findAll();
    }

    public Position findById(Long id) {
        Optional<Position> positionOptional = positionRepository.findById(id);
        return positionOptional.orElseThrow(
                () -> new PositionNotFoundException(id)
        );
    }

    private boolean isStudentEligibleForPosition(Student student, Position position) {
        return student.getClasses().contains(position.getPositionClass()) &&
                student.getStanding().equals(position.getDegree()) &&
                !position.getApplicants().contains(student);
    }
    public Position savePosition(Position position) {
        //check to see if position already exists in database with name
        if(!positionRepository.findAllByPositionClass(position.getPositionClass()).isEmpty()) {
            throw new PositionAlreadyExistsException(position.getPositionClass());
        }

        studentRepository.findAll()
                .stream()
                .filter(student -> isStudentEligibleForPosition(student, position))
                .forEach(student -> position.getApplicants().add(student));
        return positionRepository.save(position);
    }

    public Position updatePosition(Position newPosition, Long id) {
        return positionRepository.findById(id).map(position -> {
            position.setId(newPosition.getId());
            position.setPositionType(newPosition.getPositionType());
            position.setNotes(newPosition.getNotes());
            position.setDegree(newPosition.getDegree());
            position.setSemester(newPosition.getSemester());
            position.setPositionClass(newPosition.getPositionClass());
            return positionRepository.save(position);
        }).orElseThrow(
                () -> new PositionNotFoundException(id)
        );
    }

    public void deletePosition(Long id) {
        Optional<Position> positionOptional = positionRepository.findById(id);
        if(positionOptional.isPresent()) {
            positionRepository.delete(positionOptional.get());
        }
        else {
            throw new PositionNotFoundException(id);
        }
    }
}
