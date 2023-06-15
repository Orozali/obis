package kg.edu.manas.obis.controller;

import kg.edu.manas.obis.models.Student;
import kg.edu.manas.obis.services.StudentsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class AuthenticationController {

    private final StudentsService service;
    @PostMapping("/post")
    public Map<String, String> addStudent(@RequestBody Student student){
        return service.addStudent(student);
    }
    @GetMapping("/get")
    public List<Student> getAll(){
        return service.getAllStud();
    }

}
