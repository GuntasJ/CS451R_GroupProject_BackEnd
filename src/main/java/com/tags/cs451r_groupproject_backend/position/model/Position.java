package com.tags.cs451r_groupproject_backend.position.model;

import com.tags.cs451r_groupproject_backend.application.model.Application;
import com.tags.cs451r_groupproject_backend.general.Copier;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "position")
@IdClass(PositionId.class)
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
public class Position implements Copier<Position> {

    @EqualsAndHashCode.Include
    @Id
    @Column(name = "position_class")
    private String positionClass;

    @EqualsAndHashCode.Include
    @Id
    @Column(name = "position_type")
    private String positionType;

    @Column(name = "required_standing")
    private String requiredStanding;

    @Column(name = "notes")
    private String notes;

    @ElementCollection
    @CollectionTable(
            name = "running_semester",
            joinColumns = {@JoinColumn(name = "position_class"), @JoinColumn(name = "position_type")}
    )
    @Column(name = "semesters")
    private List<String> semesters;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    @JoinTable(
            name = "applying",
            joinColumns = {
                    @JoinColumn(name = "position_class"), @JoinColumn(name = "position_type")
            },
            inverseJoinColumns = @JoinColumn(name = "application_id")
    )
    @ToString.Exclude
    private List<Application> applicants = new ArrayList<>();

    public void addApplication(Application application) {
        applicants.add(application);
        application.getPositions().add(this);
    }

    public void removeApplication(Application application) {
        applicants.remove(application);
        application.getPositions().remove(this);
    }

    @Override
    public void copyFrom(Position entity) {
        this.positionClass = entity.positionClass;
        this.requiredStanding = entity.requiredStanding;
        this.semesters = entity.semesters;
        this.positionType = entity.positionType;
        this.notes = entity.notes;
        this.applicants = entity.applicants;
    }
}
