package com.tags.cs451r_groupproject_backend.filetransfer;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "files")
@NoArgsConstructor
@Getter
@Setter
public class File {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private String id;

    private String name;

    @Column(name = "file_name")
    private String type;

    @Column(name = "file_data")
    @Lob
    private byte[] data;

    public File(String name, String type, byte[] data) {
        this.name = name;
        this.type = type;
        this.data = data;
    }
}
