package kg.edu.manas.obis.controller;

import kg.edu.manas.obis.details.StudentServiceDetails;
import kg.edu.manas.obis.services.StudentsService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/main")
public class StudentController {
    private final StudentsService studentsService;
    @GetMapping()
    public String main(Model model){
//        model.addAttribute("principal",)
        return "student/main";
    }
    @GetMapping("/studentInfo")
    public String info(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        StudentServiceDetails studentServiceDetails = (StudentServiceDetails) authentication.getPrincipal();
        model.addAttribute("student",studentServiceDetails.getStudent());
        return "student/info";
    }
    @GetMapping("/lessons")
    public String lessons(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        StudentServiceDetails studentServiceDetails = (StudentServiceDetails) authentication.getPrincipal();
        model.addAttribute("lessons",studentServiceDetails.getStudent().getLessons());
        return "student/lessons";
    }
    @GetMapping("/grades")
    public String grades(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        StudentServiceDetails studentServiceDetails = (StudentServiceDetails) authentication.getPrincipal();
        model.addAttribute("lessons",studentServiceDetails.getStudent().getLessons());
        return "student/grades";
    }
    @GetMapping("/transcript")
    public String transcript(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        StudentServiceDetails studentServiceDetails = (StudentServiceDetails) authentication.getPrincipal();
        model.addAttribute("lessons",studentServiceDetails.getStudent().getLessons());
        model.addAttribute("averageGrade",studentsService.averageGrade(studentServiceDetails.getStudent()));
        model.addAttribute("allKredi",studentsService.allKredi(studentServiceDetails.getStudent()));
        System.out.println(studentsService.averageGrade(studentServiceDetails.getStudent()));
        System.out.println(studentsService.allKredi(studentServiceDetails.getStudent()));
        return "student/transcript";
    }



    // dd - 1   dc cc - 2   bc bb - 3 ba aa - 4

}
