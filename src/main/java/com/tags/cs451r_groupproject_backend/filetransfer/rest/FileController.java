package com.tags.cs451r_groupproject_backend.filetransfer.rest;

import com.tags.cs451r_groupproject_backend.filetransfer.service.FileStorageService;
import com.tags.cs451r_groupproject_backend.filetransfer.model.File;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("tags/api/v1")
@AllArgsConstructor
public class FileController {
    private FileStorageService storageService;

    @GetMapping("/files/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<byte[]> getFile(@PathVariable Long id) {
        File file = storageService.getFile(id);

        return ResponseEntity.ok()
                .header(
                        HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\"" + file.getName() + "\""
                ).body(file.getData());
    }

    @GetMapping("/files")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<ResponseFile>> getAllFiles() {
        List<ResponseFile> fileList = storageService.getAllFiles()
                .stream()
                .map(file -> {
                    String fileDownloadUri = ServletUriComponentsBuilder
                            .fromCurrentContextPath()
                            .path("/files/")
                            .path(String.valueOf(file.getId()))
                            .toUriString();
                    return new ResponseFile(
                            file.getName(),
                            fileDownloadUri,
                            file.getType(),
                            (long) file.getData().length
                    );
                }).toList();
        return ResponseEntity.ok(fileList);
    }

    @PostMapping("/upload")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> uploadFile(@RequestParam("file")MultipartFile file) {
        String message;
        try {
            storageService.store(file);
            message = "Uploaded the file successfully: " + file.getOriginalFilename();
            return ResponseEntity.ok(message);
        } catch (Exception e) {
            message = "Could not upload the file: " + file.getOriginalFilename();
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(message);
        }

    }

}
