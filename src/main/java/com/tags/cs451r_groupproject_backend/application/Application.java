package com.tags.cs451r_groupproject_backend.application;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;


@Entity
@EqualsAndHashCode
@ToString
@NoArgsConstructor
@Getter
@Setter
public class Application {
    @Id
    @GeneratedValue
    private Long id;

    private String firstName;

    private String surname;

    public Application(String firstName, String surname) {
        this.firstName = firstName;
        this.surname = surname;
    }
}
