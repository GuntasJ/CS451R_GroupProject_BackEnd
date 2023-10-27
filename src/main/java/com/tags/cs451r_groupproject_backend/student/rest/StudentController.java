package com.tags.cs451r_groupproject_backend.student.rest;

import com.tags.cs451r_groupproject_backend.student.dto.StudentDTO;
import com.tags.cs451r_groupproject_backend.student.model.Student;
import com.tags.cs451r_groupproject_backend.student.model.StudentStatus;
import com.tags.cs451r_groupproject_backend.student.service.StudentService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@CrossOrigin
@RequestMapping("tags/api/v1")
@AllArgsConstructor
public class StudentController {

    private StudentService studentService;
    @GetMapping("/students")
    @ResponseStatus(HttpStatus.OK)
    public List<StudentDTO> retrieveAllStudents() {
        return studentService.findAll()
                .stream()
                .map(StudentDTO::new)
                .toList();
    }

    @GetMapping("/students/{id}")
    @ResponseStatus(HttpStatus.OK)
    public StudentDTO retrieveStudent(@PathVariable Long id) {
        return new StudentDTO(studentService.findById(id));
    }

    @PostMapping("/students")
    @ResponseStatus(HttpStatus.CREATED)
    public StudentDTO saveStudent(@RequestBody Student student, @RequestParam(name = "file_id", required = false) Long fileId) {
        return new StudentDTO(studentService.saveStudent(student, fileId));
    }

    @PutMapping("/students/{id}")
    @ResponseStatus(HttpStatus.OK)
    public StudentDTO updateStudent(@RequestBody Student student, @PathVariable Long id) {
        return new StudentDTO(studentService.updateStudent(student, id));
    }

    @PutMapping("/students/{id}/{status}")
    @ResponseStatus(HttpStatus.OK)
    public StudentDTO updateStatus(@PathVariable Long id, @PathVariable StudentStatus status) {
        return new StudentDTO(studentService.updateStatus(id, status));
    }

    @DeleteMapping("/students/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
    }



}
