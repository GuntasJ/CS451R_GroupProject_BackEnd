package com.tags.cs451r_groupproject_backend.student.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.tags.cs451r_groupproject_backend.filetransfer.model.File;
import com.tags.cs451r_groupproject_backend.filetransfer.rest.ResponseFile;
import com.tags.cs451r_groupproject_backend.position.dto.PositionDTO;
import com.tags.cs451r_groupproject_backend.position.model.Position;
import com.tags.cs451r_groupproject_backend.student.model.Student;
import com.tags.cs451r_groupproject_backend.student.model.StudentStatus;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode
@ToString
@NoArgsConstructor
@Getter
@Setter
public class StudentDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private Long studentId;
    private String umkcEmail;
    private String standing;
    private String graduatingSemester;
    private Double umkcGPA;
    private Long hoursDoneAtUmkc;
    private String undergraduateDegree;
    private String currentMajor;
    private List<String> classes;
    private ResponseFile file;
    private StudentStatus studentStatus;

    private List<PositionDTO> positions = new ArrayList<>();

    public StudentDTO(Student student) {
        this.id = student.getId();
        this.firstName = student.getFirstName();
        this.lastName = student.getLastName();
        this.studentId = student.getStudentId();
        this.umkcEmail = student.getUmkcEmail();
        this.standing = student.getStanding();
        this.graduatingSemester = student.getGraduatingSemester();
        this.umkcGPA = student.getUmkcGPA();
        this.hoursDoneAtUmkc = student.getHoursDoneAtUmkc();
        this.undergraduateDegree = student.getUndergraduateDegree();
        this.currentMajor = student.getCurrentMajor();
        this.classes = student.getClasses();
        this.studentStatus = student.getStudentStatus();

        for(Position position : student.getPositions()) {
            PositionDTO positionDTO = new PositionDTO();
            positionDTO.copyFromPosition(position);
            positions.add(positionDTO);
        }

        String fileDownloadUri = ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .path("/files/")
                .path(String.valueOf(student.getFile().getId()))
                .toUriString();

        this.file = new ResponseFile(
                student.getFile().getName(),
                fileDownloadUri,
                student.getFile().getType(),
                (long) student.getFile().getData().length
        );
    }

    public void copyFromStudent(Student student) {
        this.id = student.getId();
        this.firstName = student.getFirstName();
        this.lastName = student.getLastName();
        this.studentId = student.getStudentId();
        this.umkcEmail = student.getUmkcEmail();
        this.standing = student.getStanding();
        this.graduatingSemester = student.getGraduatingSemester();
        this.umkcGPA = student.getUmkcGPA();
        this.hoursDoneAtUmkc = student.getHoursDoneAtUmkc();
        this.undergraduateDegree = student.getUndergraduateDegree();
        this.currentMajor = student.getCurrentMajor();
        this.classes = student.getClasses();

        String fileDownloadUri = ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .path("/files/")
                .path(String.valueOf(student.getFile().getId()))
                .toUriString();

        this.file = new ResponseFile(
                student.getFile().getName(),
                fileDownloadUri,
                student.getFile().getType(),
                (long) student.getFile().getData().length
        );

        this.studentStatus = student.getStudentStatus();
    }
}
