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
    @GeneratedValue
    private Long id;

    private String name;

    private String type;

    @Column(name = "file_data", columnDefinition = "LONGBLOB")
    @Lob
    private byte[] data;

    public File(String name, String type, byte[] data) {
        this.name = name;
        this.type = type;
        this.data = data;
    }
}
