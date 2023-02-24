package kg.edu.manas.obis.controller;

import kg.edu.manas.obis.models.Lessons;
import kg.edu.manas.obis.models.Student;
import kg.edu.manas.obis.services.LessonService;
import kg.edu.manas.obis.services.StudentsService;
import kg.edu.manas.obis.utils.JdbcRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {
    private final StudentsService studentsService;
    private final JdbcRepository jdbcRepository;
    private final LessonService lessonService;
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
    public String updated(@PathVariable("id") int id, @ModelAttribute("student") Student student){
        studentsService.update(id,student);
        return "redirect:/admin/student/"+id;
    }
    @GetMapping("/student/add")
    public String add(Model model){
        model.addAttribute("student",new Student());
        return "admin/add";
    }
    @PostMapping("/student/add")
    public String added(@ModelAttribute("student") Student student){
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
        model.addAttribute("lesson", new Lessons());
        model.addAttribute("student",studentsService.getStudentById(id));
        return "admin/lessonAdd";
    }
    @PostMapping("/lesson/{id}/add")
    public String addedLesson(@PathVariable("id") int id,@ModelAttribute("lesson") Lessons lessons){
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
}
