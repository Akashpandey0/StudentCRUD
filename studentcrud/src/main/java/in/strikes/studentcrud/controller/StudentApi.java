package in.strikes.studentcrud.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.strikes.studentcrud.entity.Student;
import in.strikes.studentcrud.service.StudentService;

@RestController
@RequestMapping("/api/students")
public class StudentApi {

    private StudentService studentService;

    public StudentApi(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping("/create")
    public Student createStudent(@RequestBody Student student) {
        Student createStudent = studentService.createStudent(student);
        return createStudent;
    }

}
