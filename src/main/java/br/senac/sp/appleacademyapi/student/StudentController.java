package br.senac.sp.appleacademyapi.student;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.senac.sp.appleacademyapi.mentor.Mentor;
import br.senac.sp.appleacademyapi.mentor.MentorRequest;
import br.senac.sp.appleacademyapi.mentor.MentorResponse;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/student")
public class StudentController {

    private final StudentService service;

    public StudentController(StudentService studentService) {
        this.service = studentService;
    }

    @GetMapping
    public List<Student> getAll() {
        System.err.println("getAll");
        return service.getAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public StudentResponse create(@RequestBody @Valid StudentRequest student) {
        return service.create(student);
    }

    @GetMapping("{id}")
    public Student getById(@PathVariable UUID id) {
        return service.getById(id);
    }

}
