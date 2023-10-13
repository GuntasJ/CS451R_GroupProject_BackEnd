package com.tags.cs451r_groupproject_backend.position.model;

import jakarta.persistence.*;
import lombok.*;

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
    private Long id;

    @Column(name = "class_type")
    private String classType;

    @Column(name = "course_number")
    private String courseNumber;

    @Column(name = "degree")
    private String degree;

    @Column(name = "semester")
    private String semester;

    @Column(name = "position_type")
    private String positionType;

    @Column(name = "notes")
    private String notes;


}
