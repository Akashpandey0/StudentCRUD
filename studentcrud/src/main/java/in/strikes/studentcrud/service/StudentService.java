package in.strikes.studentcrud.service;

import org.springframework.stereotype.Service;

import in.strikes.studentcrud.entity.Student;
import in.strikes.studentcrud.repository.StudentRepository;

@Service
public class StudentService {

    private StudentRepository studentRepo;

    public StudentService(StudentRepository studentRepo) {
        this.studentRepo = studentRepo;
    }

    public Student createStudent(Student studentReq){
        Student studentResp = studentRepo.save(studentReq);
        return studentResp;
    }
}
