package com.tags.cs451r_groupproject_backend.filetransfer.model;

import com.tags.cs451r_groupproject_backend.general.Copier;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@Data
@NoArgsConstructor
public class FileDescriptionDTO implements Copier<File> {

    private Long id;
    private String name;
    private String type;
    private Long fileLength;
    private String uriDownload;

    public FileDescriptionDTO(File file) {
        copyFrom(file);
    }
    @Override
    public void copyFrom(File entity) {
        this.name = entity.getName();
        this.type = entity.getType();
        this.fileLength = (long) entity.getData().length;
        this.id = entity.getId();
        this.uriDownload = ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .path("/files/")
                .path(String.valueOf(entity.getId()))
                .toUriString();
    }
}
