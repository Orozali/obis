package kg.edu.manas.obis.services;

import kg.edu.manas.obis.models.Teacher;
import kg.edu.manas.obis.repository.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TeacherService {
    private final TeacherRepository teacherRepository;


    public List<Teacher> findAll() {
        return teacherRepository.findAll();
    }
    @Transactional
    public void save(Teacher teacher) {
        teacherRepository.save(teacher);
    }
}
