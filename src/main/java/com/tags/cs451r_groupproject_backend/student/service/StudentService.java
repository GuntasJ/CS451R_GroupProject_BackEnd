package com.tags.cs451r_groupproject_backend.student.service;

import com.tags.cs451r_groupproject_backend.filetransfer.model.File;
import com.tags.cs451r_groupproject_backend.filetransfer.repository.FileRepository;
import com.tags.cs451r_groupproject_backend.filetransfer.rest.FileNotFoundException;
import com.tags.cs451r_groupproject_backend.position.model.Position;
import com.tags.cs451r_groupproject_backend.position.respository.PositionRepository;
import com.tags.cs451r_groupproject_backend.student.model.Student;
import com.tags.cs451r_groupproject_backend.student.model.StudentStatus;
import com.tags.cs451r_groupproject_backend.student.repository.StudentRepository;
import com.tags.cs451r_groupproject_backend.student.rest.StudentNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class StudentService {

    private StudentRepository studentRepository;
    private FileRepository fileRepository;
    private PositionRepository positionRepository;

    public List<Student> findAll() {
        return studentRepository.findAll();
    }

    public Student findById(Long id) {
        Optional<Student> studentOptional = studentRepository.findById(id);
        return studentOptional.orElseThrow(
                () -> new StudentNotFoundException(id)
        );
    }

    public Student saveStudent(Student student, Long fileId) {
        student.getClasses().forEach(c -> {
            List<Position> positions = positionRepository.findAllByPositionClass(c);
            if(!positions.isEmpty()) {
                positions.get(0).getApplicants().add(student);
            }
        });

        if(fileId == null) {
            return studentRepository.save(student);
        }
        Optional<File> fileOptional = fileRepository.findById(fileId);
        if(fileOptional.isPresent()) {
            student.setFile(fileOptional.get());
            return studentRepository.save(student);
        }
        throw new FileNotFoundException(fileId);
    }

    public Student updateStudent(Student student, Long id) {
        return studentRepository.findById(id).map(student1 -> {
            student1.copyFrom(student);
            return studentRepository.save(student1);
        }).orElseThrow(() -> new StudentNotFoundException(id));
    }

    public Student updateStatus(Long id, StudentStatus status) {
        Optional<Student> studentOptional = studentRepository.findById(id);
        if(studentOptional.isPresent()) {
            studentOptional.get().setStudentStatus(status);
            return studentOptional.get();
        }
        else {
            throw new StudentNotFoundException(id);
        }
    }

    public void deleteStudent(Long id) {
        Optional<Student> studentOptional = studentRepository.findById(id);
        if(studentOptional.isPresent()) {
            studentRepository.delete(studentOptional.get());
        }
        else {
            throw new StudentNotFoundException(id);
        }
    }


}
