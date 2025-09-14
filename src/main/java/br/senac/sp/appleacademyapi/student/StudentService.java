package br.senac.sp.appleacademyapi.student;

import java.util.List;
import java.util.UUID;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.senac.sp.appleacademyapi.mentor.MentorResponse;
import br.senac.sp.appleacademyapi.security.AuthUserRepository;
import jakarta.validation.Valid;

@Service
public class StudentService {

    private final AuthUserRepository authUserRepository;

    private final PasswordEncoder passwordEncoder;
    private final StudentRepository repository;

    public StudentService(StudentRepository studentRepository, PasswordEncoder passwordEncoder, AuthUserRepository authUserRepository) {
        this.repository = studentRepository;
        this.passwordEncoder = passwordEncoder;
        this.authUserRepository = authUserRepository;
    }

    public List<Student> getAll() {
        return repository.findAll();
    }

    public StudentResponse create(StudentRequest studentRequest) {
        var student = repository.save(studentRequest.toStudent());
        var authUser = studentRequest.toAuthUser(student.getId());
        authUser.setPassword(passwordEncoder.encode(authUser.getPassword()));
        authUserRepository.save(authUser);
        return StudentResponse.from(student, authUser);
    }

    public Student getById(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Student not found with id: " + id));
    }
    
}
