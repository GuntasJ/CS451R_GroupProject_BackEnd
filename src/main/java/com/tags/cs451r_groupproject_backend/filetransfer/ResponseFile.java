package com.tags.cs451r_groupproject_backend.filetransfer;

import lombok.*;

@AllArgsConstructor
@Setter
@Getter
@NoArgsConstructor
public class ResponseFile {
    private String name;
    private String url;
    private String type;
    private Long size;
}
