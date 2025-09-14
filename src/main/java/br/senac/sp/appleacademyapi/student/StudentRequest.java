package br.senac.sp.appleacademyapi.student;

import java.time.LocalDate;
import java.util.UUID;

import br.senac.sp.appleacademyapi.cohort.Cohort;
import br.senac.sp.appleacademyapi.mentor.Expertise;
import br.senac.sp.appleacademyapi.security.AuthUser;
import br.senac.sp.appleacademyapi.security.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record StudentRequest(
    @NotBlank String name,
    @Email String email,
    String password,
    String avatarPath,
    Expertise expertise,
    String studentId,
    DeliveryMode deliveryMode,
    Program program,
    LocalDate birthDate,
    Cohort cohort
) {
     public Student toStudent(){
        return Student.builder()
            .name(name)
            .avatarPath(avatarPath)
            .expertise(expertise)
            .studentId(studentId)
            .deliveryMode(deliveryMode)
            .program(program)
            .birthDate(birthDate)
            .cohort(cohort)
            .build();
    }

    public AuthUser toAuthUser(UUID studentId){
        return AuthUser.builder()
            .email(email)
            .password(password)
            .role(Role.STUDENT)
            .referenceId(studentId)
            .build();
    }
}
