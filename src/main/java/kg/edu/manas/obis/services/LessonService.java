package kg.edu.manas.obis.services;

import kg.edu.manas.obis.models.Lessons;
import kg.edu.manas.obis.models.Student;
import kg.edu.manas.obis.repository.LessonRepository;
import kg.edu.manas.obis.utils.JdbcInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class LessonService{
    private final JdbcTemplate jdbcTemplate;
    private final LessonRepository lessonRepository;

    @Transactional
    public void addGrade(int id, Lessons lessons){
        Optional<Lessons> lessons1 = lessonRepository.findById(id);
        if(lessons1.isPresent()){
                int grade = Integer.parseInt(lessons.getGrades());
                if(grade<50){
                    lessons1.get().setGrades("FF");
                }else if(grade<=60){
                    lessons1.get().setGrades("DD");
                }else if(grade<=70){
                    lessons1.get().setGrades("DC");
                }else if(grade<=75){
                    lessons1.get().setGrades("CC");
                }else if(grade<=80){
                    lessons1.get().setGrades("CB");
                }else if(grade<=85){
                    lessons1.get().setGrades("BB");
                }else if(grade<=90){
                    lessons1.get().setGrades("BA");
                }else if(grade<=100){
                    lessons1.get().setGrades("AA");
                }
        }
    }

    public Student findStudentByLessonId(int id) {
        return lessonRepository.findById(id).get().getStudent();
    }
    @Transactional
    public void clearGrade(int id) {
        Optional<Lessons> lessons = lessonRepository.findById(id);
        lessons.ifPresent(value -> value.setGrades(null));
    }

    public List<Lessons> findLessonsByTeacherId(int id) {
        return lessonRepository.findAllByTeacherId(id);
    }
    public Set<Student> getStudentsByLessons(List<Lessons> lessons) {
        Set<Student> students = new HashSet<>();
        for(Lessons lesson: lessons){
            List<Student> studentList = lessonRepository.findStudentByTeacherId(lesson.getTeacher().getId());
            students.addAll(studentList);
        }
        return students;
    }

//    @Override
//    public void saveWithJDBC(Student student,Lessons lessons) {
//
//    }
}
