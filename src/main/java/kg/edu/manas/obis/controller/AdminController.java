package kg.edu.manas.obis.controller;

import jakarta.validation.Valid;
import kg.edu.manas.obis.dto.LessonDTO;
import kg.edu.manas.obis.dto.LessonMark;
import kg.edu.manas.obis.models.Lessons;
import kg.edu.manas.obis.models.Student;
import kg.edu.manas.obis.models.Teacher;
import kg.edu.manas.obis.services.LessonService;
import kg.edu.manas.obis.services.StudentsService;
import kg.edu.manas.obis.services.TeacherService;
import kg.edu.manas.obis.utils.JdbcRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {
    private final StudentsService studentsService;
    private final JdbcRepository jdbcRepository;
    private final ModelMapper modelMapper;
    private final LessonService lessonService;
    private final TeacherService teacherService;
    @GetMapping()
    public String mainAdmin(Model model){
        model.addAttribute("students",studentsService.allStudents());
         return "admin/adminMain";
    }

    @GetMapping("/student/{id}")
    public String show(@PathVariable("id") int id,Model model){
        model.addAttribute("student",studentsService.getStudentById(id));
        return "admin/studentInfo";
    }
    @GetMapping("/student/{id}/update")
    public String update(@PathVariable("id") int id,Model model){
        model.addAttribute("student",studentsService.getStudentById(id));
        return "admin/update";
    }
//    @GetMapping("/student/attendance")
//    public String attendance(Model model){
//        model.addAttribute("students",studentsService.allStudents());
//        return "admin/attendance";
//    }
    @PostMapping("/student/{id}")
    public String updated(@PathVariable("id") int id, @ModelAttribute("student") @Valid Student student
                          , BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "admin/update";
        }
        studentsService.update(id,student);
        return "redirect:/admin/student/"+id;
    }
    @GetMapping("/student/add")
    public String add(Model model){
        model.addAttribute("student",new Student());
        return "admin/add";
    }
    @PostMapping("/student/add")
    public String added(@ModelAttribute("student") @Valid Student student
                        ,BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "admin/add";
        }
        studentsService.add(student);
        return "redirect:/admin";
    }
    @GetMapping("/student/{id}/lessons")
    public String listOfLessons(@PathVariable("id") int id, Model model){
        model.addAttribute("student",studentsService.getStudentById(id));
        model.addAttribute("lessons", studentsService.getStudentById(id).getLessons());
        model.addAttribute("markLesson",new Lessons());
        return "admin/lessonsOfStudent";
    }
    @GetMapping("/lesson/{id}/add")
    public String addLesson(@PathVariable("id") int id, Model model){
        model.addAttribute("lesson", new LessonDTO());
        model.addAttribute("student",studentsService.getStudentById(id));
        model.addAttribute("teachers",teacherService.findAll());
        return "admin/lessonAdd";
    }
    @PostMapping("/lesson/{id}/add")
    public String addedLesson(@PathVariable("id") int id,@ModelAttribute("lesson") @Valid LessonDTO lessonDTO
                              ,BindingResult bindingResult){
        Lessons lessons = convert(lessonDTO);
        if(bindingResult.hasErrors()){
            return "admin/lessonAdd";
        }
        Student student = studentsService.getStudentById(id);
        jdbcRepository.saveWithJDBC(student,lessons);
        return "redirect:/admin/student/"+id+"/lessons";
    }
//    @GetMapping("/lesson/{id}/delete")
//    public String delete(Model model,@PathVariable("id") int id){
//        model.addAttribute("student",studentsService.getStudentById(id));
//        model.addAttribute("lessons",studentsService.getStudentById(id).getLessons());
//        return "admin/deleteLesson";
//    }
//    @PostMapping("/lesson/{id}/delete")
//    public String delete(@ModelAttribute("lessons") List<Lessons> lessons,@PathVariable("id") int id){
//        Student student1 = studentsService.getStudentById(id);
//        List<Lessons> lessonsList = student1.getLessons();
//        jdbcRepository.deleteLessons(lessonsList);
//        return "redirect:/admin";
//    }
    @PostMapping("/lesson/{id}/mark")
    public String mark(@PathVariable("id") int id,@ModelAttribute("markLesson") Lessons lessons){
//        if(bindingResult.hasErrors()){
//            return "admin/lessonsOfStudent";
//        }
            lessonService.addGrade(id,lessons);
        Student student = lessonService.findStudentByLessonId(id);
        return "redirect:/admin/student/"+student.getId()+"/lessons";
    }
    @PostMapping("/lesson/{id}/clear")
    public String clear(@PathVariable("id") int id){
        lessonService.clearGrade(id);
        Student student = lessonService.findStudentByLessonId(id);
        return "redirect:/admin/student/"+student.getId()+"/lessons";
    }

    @PostMapping("/student/search")
    public String search(Model model,@RequestParam("keyword") String string){
        List<Student> allStudents = studentsService.findByKeyword(string);
        System.out.println(allStudents);
        model.addAttribute("students",allStudents);
        return "admin/search";
    }

    @GetMapping("/teachers")
    public String teachers(Model model){
        model.addAttribute("teachers",teacherService.findAll());
        return "admin/teachers/teachers";
    }
    @GetMapping("/teacher/add")
    public String addTeacher(Model model){
        model.addAttribute("teachers",new Teacher());
        return "admin/teachers/add";
    }
    @PostMapping("/teacher/add")
    public String addedTeacher(@ModelAttribute("teachers") @Valid Teacher teacher
                               ,BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "admin/teachers/add";
        }
        teacherService.save(teacher);
        return "redirect:/admin/teachers";
    }

    @GetMapping("/teacher/{id}")
    public String teacherInfo(@PathVariable("id") int id,Model model){
        Teacher teacher = teacherService.findById(id);
        List<Lessons> lessons = lessonService.findLessonsByTeacherId(id);
        Set<Student> students = lessonService.getStudentsByLessons(lessons);
        model.addAttribute("students",students);
        return "admin/teachers/teacherInfo";
    }







    public Lessons convert(LessonDTO lessonDTO){
        return modelMapper.map(lessonDTO,Lessons.class);
    }
    public Lessons convert(LessonMark lessonMark){
        return modelMapper.map(lessonMark,Lessons.class);
    }

//    @ExceptionHandler
//    public String numberException(NumberFormatException exception){
//
//    }
}
