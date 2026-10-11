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

    public Student createStudent(Student student){
        System.out.println("Service");
        // Student studentResp = studentRepo.saveStudent(student);
        System.out.println("Existing service");
        Student s1 = new Student();
        s1.setName("Akash Pandey");
        s1.setAge(21);
        s1.setEmail("7326");
        s1.setRollNo(434323432L);
        s1.setSubject("Java");
        return s1;
    }
}
