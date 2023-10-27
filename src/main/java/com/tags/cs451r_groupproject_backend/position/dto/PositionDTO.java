package com.tags.cs451r_groupproject_backend.position.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.tags.cs451r_groupproject_backend.position.model.Position;
import com.tags.cs451r_groupproject_backend.student.dto.StudentDTO;
import com.tags.cs451r_groupproject_backend.student.model.Student;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode
@ToString
@NoArgsConstructor
@Getter
@Setter
public class PositionDTO {
    private Long id;
    private String positionClass;
    private String degree;
    private List<String> semester;
    private String positionType;
    private String notes;

    private List<StudentDTO> applicants = new ArrayList<>();

    public PositionDTO(Position position) {
        this.id = position.getId();
        this.positionClass = position.getPositionClass();
        this.degree = position.getDegree();
        this.semester = position.getSemester();
        this.positionType = position.getPositionType();
        this.notes = position.getNotes();

        for(Student applicant : position.getApplicants()) {
            StudentDTO studentDTO = new StudentDTO();
            studentDTO.copyFromStudent(applicant);
            applicants.add(studentDTO);
        }
    }

    public void copyFromPosition(Position position) {
        this.id = position.getId();
        this.positionClass = position.getPositionClass();
        this.degree = position.getDegree();
        this.semester = position.getSemester();
        this.positionType = position.getPositionType();
        this.notes = position.getNotes();
    }

}
