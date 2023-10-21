package com.tags.cs451r_groupproject_backend.filetransfer.model;

import com.tags.cs451r_groupproject_backend.student.model.Student;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "file")
@NoArgsConstructor
@Getter
@Setter
public class File {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "f_id")
    private Long id;

    @Column(name = "f_name")
    private String name;

    @Column(name = "f_type")
    private String type;

    @Column(name = "f_data", columnDefinition = "LONGBLOB")
    @Lob
    private byte[] data;

    public File(String name, String type, byte[] data) {
        this.name = name;
        this.type = type;
        this.data = data;
    }
}
