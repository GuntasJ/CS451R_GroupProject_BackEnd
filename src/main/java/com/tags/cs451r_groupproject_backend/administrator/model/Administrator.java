package com.tags.cs451r_groupproject_backend.administrator.model;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "administrator")
@EqualsAndHashCode
@ToString
@NoArgsConstructor
@Getter
@Setter
public class Administrator {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "adm_id")
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    public Administrator(String firstName, String surname) {
        this.firstName = firstName;
        this.lastName = surname;
    }
}
