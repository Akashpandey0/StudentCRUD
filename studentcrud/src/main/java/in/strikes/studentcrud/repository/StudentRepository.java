package in.strikes.studentcrud.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.strikes.studentcrud.entity.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

}
