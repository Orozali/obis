package kg.edu.manas.obis.repository;

import kg.edu.manas.obis.models.Lessons;
import kg.edu.manas.obis.models.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LessonRepository extends JpaRepository<Lessons,Integer> {
    @Query("select t from Lessons t where t.teacher.id=?1")
    List<Lessons> findAllByTeacherId(int id);
    @Query("select t.student from Lessons t where t.teacher.id=?1")
    List<Student> findStudentByTeacherId(int id);
}
