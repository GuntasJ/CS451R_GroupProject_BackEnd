package com.tags.cs451r_groupproject_backend.student.rest;

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
    public Student saveStudent(@RequestBody Student student, @RequestParam(name = "file_id", required = false) Long fileId) {
        return studentService.saveStudent(student, fileId);
    }

    @PutMapping("/students/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Student updateStudent(@RequestBody Student student, @PathVariable Long id) {
        return studentService.updateStudent(student, id);
    }

    @PutMapping("/students/{id}/{status}")
    @ResponseStatus(HttpStatus.OK)
    public Student updateStatus(@PathVariable Long id, @PathVariable StudentStatus status) {
        return studentService.updateStatus(id, status);
    }

    @DeleteMapping("/students/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
    }



}
