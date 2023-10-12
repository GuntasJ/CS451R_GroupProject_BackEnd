package com.tags.cs451r_groupproject_backend.filetransfer.service;

import com.tags.cs451r_groupproject_backend.filetransfer.model.File;
import com.tags.cs451r_groupproject_backend.filetransfer.repository.FileRepository;
import com.tags.cs451r_groupproject_backend.filetransfer.rest.FileNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

@Service
@AllArgsConstructor
public class FileStorageService {
    private FileRepository fileRepository;

    public File store(MultipartFile file) throws IOException {
        String fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
        File file1 = new File(fileName, file.getContentType(), file.getBytes());

        return fileRepository.save(file1);
    }

    public File getFile(Long id)  {
        return fileRepository.findById(id)
                .orElseThrow(() -> new FileNotFoundException(id));
    }

    public List<File> getAllFiles() {
        return fileRepository.findAll();
    }
}
