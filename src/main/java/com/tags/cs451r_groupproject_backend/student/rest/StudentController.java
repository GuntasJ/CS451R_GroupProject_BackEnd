package com.tags.cs451r_groupproject_backend.student.rest;

import com.tags.cs451r_groupproject_backend.student.model.Student;
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

    private final StudentService studentService;
    @GetMapping("/students")
    @ResponseStatus(HttpStatus.OK)
    public List<Student> retrieveAllStudents() {
        return studentService.findAll();
    }

    @GetMapping("/students/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Student retrieveStudent(@PathVariable Long id) {
        return studentService.findById(id);
    }

    @PostMapping("/students")
    @ResponseStatus(HttpStatus.CREATED)
    public Student saveStudent(@RequestBody Student student) {
        return studentService.saveStudent(student);
    }

    @PutMapping("/students/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Student updateStudent(@RequestBody Student student, @PathVariable Long id) {
        return studentService.updateStudent(student, id);
    }

    @DeleteMapping("/students/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
    }



}
