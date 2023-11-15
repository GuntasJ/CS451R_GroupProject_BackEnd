package com.tags.cs451r_groupproject_backend.application.model;

import com.tags.cs451r_groupproject_backend.filetransfer.model.File;
import com.tags.cs451r_groupproject_backend.general.Copier;
import com.tags.cs451r_groupproject_backend.position.model.Position;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Entity
@Table(name = "application")
@Data
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Application implements Copier<Application> {
    @Id
    @GeneratedValue
    @EqualsAndHashCode.Include
    @Column(name = "application_id")
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "student_id")
    private Long studentId;

    @Column(name = "umkc_email")
    private String umkcEmail;

    @Column(name = "standing")
    private String standing;

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

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private ApplicationStatus applicationStatus = ApplicationStatus.PENDING;

    @ElementCollection
    @CollectionTable(name = "desired_class", joinColumns = @JoinColumn(name = "application_id"))
    @Column(name = "class_name")
    private List<String> desiredClasses;

    @ElementCollection
    @CollectionTable(name = "desired_type", joinColumns = @JoinColumn(name = "application_id"))
    @Column(name = "type")
    private List<String> desiredTypes;

    @OneToMany(cascade = {
            CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH
    }, orphanRemoval = true)
    @JoinColumn(name = "owner_application_id", referencedColumnName = "application_id")
    private List<File> files = new ArrayList<>();

    @ManyToMany(mappedBy = "applicants",
            cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH}
    )
    private List<Position> positions = new ArrayList<>();

    public void addFile(File file) {
        if(!files.contains(file)) {
            files.add(file);
        }
    }

    public void removeFile(File file) {
        files.remove(file);
    }

    public void addPosition(Position position) {
        positions.add(position);
        position.getApplicants().add(this);
    }

    public void removePosition(Position position) {
        positions.remove(position);
        position.getApplicants().remove(this);
    }

    @Override
    public void copyFrom(Application entity) {
        this.firstName = entity.firstName;
        this.lastName = entity.lastName;
        this.studentId = entity.studentId;
        this.umkcEmail = entity.umkcEmail;
        this.standing = entity.standing;
        this.graduatingSemester = entity.graduatingSemester;
        this.umkcGPA = entity.umkcGPA;
        this.hoursDoneAtUmkc = entity.hoursDoneAtUmkc;
        this.undergraduateDegree = entity.undergraduateDegree;
        this.currentMajor = entity.currentMajor;
        this.applicationStatus = entity.applicationStatus;
        this.desiredClasses = entity.desiredClasses;
        this.desiredTypes = entity.desiredTypes;
        this.files = entity.files;
        this.positions = entity.positions;
    }
}
