package kg.edu.manas.obis.utils;

import kg.edu.manas.obis.models.Lessons;
import kg.edu.manas.obis.models.Student;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class JdbcRepository implements JdbcInterface{
    private final JdbcTemplate jdbcTemplate;

    public JdbcRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void saveWithJDBC(Student student, Lessons lessons) {
        jdbcTemplate.update("INSERT INTO Lessons (lesson_code,lesson_name,kredi,student_id,teacher_id) VALUES(?,?,?,?,?)",
                new Object[] { lessons.getLesson_code(), lessons.getLesson_name(), lessons.getKredi(), student.getId(),lessons.getTeacher().getId()});
    }
    @Override
    public void deleteLessons(Student student,List<Lessons> lessonsList) {
        for(Lessons lessons: lessonsList){
            jdbcTemplate.update("delete from Lessons where id=? and where student_id=",
                   lessons.getId());
        }
    }

}
