package kg.edu.manas.obis.services;

import kg.edu.manas.obis.details.StudentServiceDetails;
import kg.edu.manas.obis.models.Lessons;
import kg.edu.manas.obis.models.Student;
import kg.edu.manas.obis.repository.StudentsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class StudentsService implements UserDetailsService {
    private final StudentsRepository studentsRepository;
    private final PasswordEncoder passwordEncoder;

    public List<Student> allStudents(){
        return studentsRepository.findStudentsByRole("ROLE_USER",Sort.by("id"));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Student> student = studentsRepository.findStudentByStudent_number(username);
        if(student.isEmpty())
            throw new UsernameNotFoundException("User not found");
        return new StudentServiceDetails(student.get());
    }

    public Student getStudentById(int id) {
        Optional<Student> student = studentsRepository.findById(id);
        return student.orElse(null);
    }
    @Transactional
    public void update(int id, Student s) {
        s.setId(id);
        s.setPassword(passwordEncoder.encode(s.getPassword()));
        s.setRole("ROLE_USER");
        studentsRepository.save(s);
    }
    @Transactional
    public void add(Student student) {
        student.setRole("ROLE_USER");
        student.setPassword(passwordEncoder.encode(student.getPassword()));
        studentsRepository.save(student);
    }

    public void deleteLessons(Student student1, List<Lessons> lessonsList) {
    }
    public double averageGrade(Student student) {
        List<Lessons> lessons = student.getLessons();
        double averageGrade = 0;
        for(Lessons lesson:lessons){
            if(Objects.equals(lesson.getGrades(), "FF")){
                averageGrade+=0;
            }else if(Objects.equals(lesson.getGrades(), "DD")){
                averageGrade+=1;
            }else if(Objects.equals(lesson.getGrades(), "DC") || Objects.equals(lesson.getGrades(), "CC")){
                averageGrade+=2;
            }else if(Objects.equals(lesson.getGrades(), "BC") || Objects.equals(lesson.getGrades(), "BB")){
                averageGrade+=3;
            }else if(Objects.equals(lesson.getGrades(), "BA") || Objects.equals(lesson.getGrades(), "AA")){
                averageGrade+=4;
            }
        }
        double result = averageGrade/lessons.size();
        result*=100;
        result = Math.round(result);
        result/=100;

        return result;
    }

    public int allKredi(Student student) {
        List<Lessons> lessons = student.getLessons();
        int allKredi = 0;
        for(Lessons lesson:lessons){
            allKredi+=lesson.getKredi();
        }
        return allKredi;
    }

    public List<Student> findByKeyword(String string) {
        return studentsRepository.findAllByNameContaining(string);
    }


}
