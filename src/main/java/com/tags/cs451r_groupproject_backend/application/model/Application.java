package com.tags.cs451r_groupproject_backend.application.model;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "application")
@EqualsAndHashCode
@ToString
@NoArgsConstructor
@Getter
@Setter
public class Application {
    @Id
    @GeneratedValue
    @Column(name = "app_id")
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    public Application(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }
}
