package kg.edu.manas.obis.utils;

import kg.edu.manas.obis.models.Lessons;
import kg.edu.manas.obis.models.Student;

import java.util.List;

public interface JdbcInterface {

    void saveWithJDBC(Student student, Lessons lessons);

    void deleteLessons(Student student1, List<Lessons> lessonsList);

//    void deleteLessons(List<Lessons> lessonsList);
}
