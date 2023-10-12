package com.tags.cs451r_groupproject_backend.student.model;


import jakarta.persistence.*;
import lombok.*;

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

    public Student(String firstName, String surname) {
        this.firstName = firstName;
        this.lastName = surname;
    }
}
