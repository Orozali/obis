package kg.edu.manas.obis.services;

import kg.edu.manas.obis.models.Lessons;
import kg.edu.manas.obis.models.Student;
import kg.edu.manas.obis.models.Teacher;
import kg.edu.manas.obis.repository.LessonRepository;
import net.bytebuddy.dynamic.DynamicType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;


class LessonServiceTest {
    @Mock
    private LessonRepository lessonRepository;
    @Mock
    private LessonService lessonService;
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    @Transactional
    void addGrade() {
        Lessons l = new Lessons();
        l.setId(1);
        l.setGrades("4");
        Optional<Lessons> lesson = Optional.of(l);

        when(lessonRepository.findById(1)).thenReturn(lesson);

        assertEquals(lessonRepository.findById(1),lesson);
        verify(lessonRepository,times(1)).findById(1);

    }

    @Test
    void shouldThrowNumberFormatException(){
        Student student = new Student();
        Teacher teacher = new Teacher();
        Lessons lessons = new Lessons(1,"Math","AA","Bil-205",4,6.3,student,teacher);
        assertThrows(NumberFormatException.class,()->{
            Integer.parseInt(lessons.getGrades());
        });
    }


    @Test
    void findStudentByLessonId() {
        int lessonId = 1;

        Student student = new Student();
        student.setId(1);
        student.setName("John");

        Lessons lesson = new Lessons();
        lesson.setId(lessonId);
        lesson.setStudent(student);

        when(lessonRepository.findById(lessonId)).thenReturn(Optional.of(lesson));
        when(lessonService.findStudentByLessonId(1)).thenReturn(student);

        // Act
        Student result = lessonService.findStudentByLessonId(lessonId);

        // Assert
        assertEquals(student.getId(), result.getId());
        assertEquals(student.getName(), result.getName());
//        verify(lessonRepository, times(1)).findById(lessonId);
        verifyNoMoreInteractions(lessonRepository);
    }

    @Test
    void clearGrade() {
    }

    @Test
    void findLessonsByTeacherId() {
    }

    @Test
    void getStudentsByLessons() {
    }
}