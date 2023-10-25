package com.tags.cs451r_groupproject_backend.position.model;

import com.tags.cs451r_groupproject_backend.student.model.Student;
import jakarta.persistence.*;
import lombok.*;

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

    @Column(name = "p_class")
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

    @OneToMany
    @JoinColumn(name = "position_id")
    private List<Student> applicants;

}
