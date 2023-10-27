package com.tags.cs451r_groupproject_backend.position.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tags.cs451r_groupproject_backend.student.model.Student;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "position")
@EqualsAndHashCode
@ToString
@NoArgsConstructor
@Getter
@Setter
public class Position {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "position_class")
    private String positionClass;

    @Column(name = "degree")
    private String degree;

    @ElementCollection
    @Column(name = "semester")
    private List<String> semester;

    @Column(name = "position_type")
    private String positionType;

    @Column(name = "notes")
    private String notes;

    @ManyToMany(
            cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH}
    )
    @JoinTable(
            name = "position_student",
            joinColumns = @JoinColumn(name = "position_id"),
            inverseJoinColumns = @JoinColumn(name = "student_id")
    )

    private List<Student> applicants = new ArrayList<>();
}
