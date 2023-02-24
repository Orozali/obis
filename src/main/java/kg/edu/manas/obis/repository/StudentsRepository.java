package kg.edu.manas.obis.repository;

import kg.edu.manas.obis.models.Student;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentsRepository extends JpaRepository<Student, Integer> {
    @Query("select t from Student t where t.student_number = ?1")
    Optional<Student> findStudentByStudent_number(String st_number);
    @Query("select t from Student t where t.role = ?1")
    List<Student> findStudentsByRole(String role,Sort sort);
    List<Student> findAllByNameContaining(String s);

}
