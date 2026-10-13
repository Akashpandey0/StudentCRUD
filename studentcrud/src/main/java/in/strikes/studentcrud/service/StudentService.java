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
        studentReq.setDeleted(false);
        Student studentResp = studentRepo.save(studentReq);
        return studentResp;
    }

    public Student getStudent(Long id) {
        Optional<Student> studentResp = studentRepo.findByIdAndDeletedIsFalse(id);
        if(studentResp.isPresent()) {
            return studentResp.get();
        }

        return null;
    }

    public List<Student> getAllStudents() {
        List<Student> studentList = studentRepo.findByDeletedIsFalse();
        return studentList;
    }

    public Student updateStudent(Long id, Student studentDetails) {
        Optional<Student> existingStudent = studentRepo.findByIdAndDeletedIsFalse(id);

        if(existingStudent.isEmpty()) return null;

        Student studentToSave = existingStudent.get();
        studentToSave.setName(studentDetails.getName());
        studentToSave.setEmail(studentDetails.getEmail());
        studentToSave.setAge(studentDetails.getAge());
        studentToSave.setRollNo(studentDetails.getRollNo());
        studentToSave.setSubject(studentDetails.getSubject());
        studentToSave.setDeleted(false);

        return studentRepo.save(studentToSave);
    }

    public Boolean deleteById(Long id) {
        if (!studentRepo.existsById(id)) {
            return false;
        }
        studentRepo.deleteById(id);
        return true;
    }

    public Boolean deleteStudentSoftDelete(Long id) {
        Optional<Student> existingStudent = studentRepo.findByIdAndDeletedIsFalse(id);

        if(existingStudent.isEmpty()) return false;

        Student studentToSave = existingStudent.get();
        studentToSave.setDeleted(true);

        studentRepo.save(studentToSave);
        return true;
    }
}
