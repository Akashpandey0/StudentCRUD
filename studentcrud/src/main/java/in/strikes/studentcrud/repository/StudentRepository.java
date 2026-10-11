package in.strikes.studentcrud.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import in.strikes.studentcrud.entity.Student;

@Component
public class StudentRepository /*extends JpaRepository<Student, Long>*/ {
    public Student saveStudent(Student studentReq) {
        System.out.println("Repository");
        System.out.println("existing repo");
        return null;
    }

}
