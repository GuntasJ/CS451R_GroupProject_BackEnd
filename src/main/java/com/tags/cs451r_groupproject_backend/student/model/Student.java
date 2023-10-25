package com.tags.cs451r_groupproject_backend.student.model;


import com.tags.cs451r_groupproject_backend.filetransfer.model.File;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "student")
@EqualsAndHashCode
@ToString
@NoArgsConstructor
@Getter
@Setter
public class Student {
    @Id
    @GeneratedValue
    @Column(name = "s_id")
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "student_id")
    private Long studentId;

    @Column(name = "umkc_email")
    private String umkcEmail;

    @Column(name = "current_level")
    private String currentLevel;

    @Column(name = "graduating_semester")
    private String graduatingSemester;

    @Column(name = "umkc_gpa")
    private Double umkcGPA;

    @Column(name = "hours_done_at_umkc")
    private Long hoursDoneAtUmkc;

    @Column(name = "undergraduate_degree")
    private String undergraduateDegree;

    @Column(name = "current_major")
    private String currentMajor;

    @ElementCollection
    @Column(name = "classes")
    private List<String> classes;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "file_id", referencedColumnName = "f_id")
    private File file;

    @Column(name = "status")
    private StudentStatus studentStatus = StudentStatus.PENDING;

    public Student(String firstName, String lastName, Long studentId, String umkcEmail, String currentLevel, String graduatingSemester, Double umkcGPA, Long hoursDoneAtUmkc, String undergraduateDegree, String currentMajor, List<String> positionClasses) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.studentId = studentId;
        this.umkcEmail = umkcEmail;
        this.currentLevel = currentLevel;
        this.graduatingSemester = graduatingSemester;
        this.umkcGPA = umkcGPA;
        this.hoursDoneAtUmkc = hoursDoneAtUmkc;
        this.undergraduateDegree = undergraduateDegree;
        this.currentMajor = currentMajor;
    }

    public void copyFrom(Student student) {
        this.firstName = student.firstName;
        this.lastName = student.lastName;
        this.studentId = student.studentId;
        this.umkcEmail = student.umkcEmail;
        this.currentLevel = student.currentLevel;
        this.graduatingSemester = student.graduatingSemester;
        this.umkcGPA = student.umkcGPA;
        this.hoursDoneAtUmkc = student.hoursDoneAtUmkc;
        this.undergraduateDegree = student.undergraduateDegree;
        this.currentMajor = student.currentMajor;
    }
}
