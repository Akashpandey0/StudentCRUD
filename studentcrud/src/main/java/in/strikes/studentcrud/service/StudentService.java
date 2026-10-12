package in.strikes.studentcrud.service;

import java.util.List;
import java.util.Optional;

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

    public Student getStudent(Long id) {
        Optional<Student> studentResp = studentRepo.findById(id);
        if(studentResp.isPresent()) {
            return studentResp.get();
        }

        return null;
    }

    public List<Student> getAllStudents() {
        List<Student> studentList = studentRepo.findAll();
        return studentList;
    }

    public Student updateStudent(Long id, Student studentDetails) {
        Optional<Student> existingStudent = studentRepo.findById(id);

        if(existingStudent.isEmpty()) return null;

        Student studentToSave = existingStudent.get();
        studentToSave.setName(studentDetails.getName());
        studentToSave.setEmail(studentDetails.getEmail());
        studentToSave.setAge(studentDetails.getAge());
        studentToSave.setRollNo(studentDetails.getRollNo());
        studentToSave.setSubject(studentDetails.getSubject());

        return studentRepo.save(studentToSave);
    }

    public Boolean deleteById(Long id) {
        if (!studentRepo.existsById(id)) {
            return false;
        }
        studentRepo.deleteById(id);
        return true;
    }
}
