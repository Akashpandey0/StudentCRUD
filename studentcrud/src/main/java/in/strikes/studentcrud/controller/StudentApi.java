package in.strikes.studentcrud.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
    public ResponseEntity<Student> createStudent(@RequestBody Student student) {
        Student createStudent = studentService.createStudent(student);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(createStudent);
    }

    // read one student
    @GetMapping ("/get")
    public ResponseEntity<Student> getStudent(@RequestParam Long id) {
        Student studentResp = studentService.getStudent(id);

        if(studentResp == null) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .build();
        }
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(studentResp);
    }

    // Read all
    @GetMapping("/getAll")
    public ResponseEntity<List<Student>> getAllStudents() {
        List<Student> studentList = studentService.getAllStudents();

        if(studentList.isEmpty()) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .build();
        }
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(studentList);
    }

    // update student
    @PutMapping ("/update")
    public ResponseEntity<Student> updateStudent(@RequestParam Long id, @RequestBody Student studentDetails) {
        Student existingStudent = studentService.updateStudent(id, studentDetails);

        if(existingStudent == null) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .build();
        }

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(existingStudent);
    }

    //Delete By Id
    @DeleteMapping ("/delete")
    public ResponseEntity<String> deleteById(@RequestParam Long id) {
        Boolean isDeleted = studentService.deleteById(id);

        if(!isDeleted) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity
                .status(HttpStatus.OK)
                .body("Record Deleted");
    }

    @PatchMapping("/softDelete")
    public ResponseEntity<String> deleteStudentSoftDelete(@RequestParam Long id) {
        Boolean isDeleted = studentService.deleteStudentSoftDelete(id);

        if(!isDeleted) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return ResponseEntity
                .status(HttpStatus.OK)
                .body("Record deleted softly");
    }
}
