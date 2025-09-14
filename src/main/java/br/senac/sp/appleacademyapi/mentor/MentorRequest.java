package br.senac.sp.appleacademyapi.mentor;

import java.util.UUID;

import br.senac.sp.appleacademyapi.security.AuthUser;
import br.senac.sp.appleacademyapi.security.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record MentorRequest(
    @NotBlank
    String name,
    String avatarPath,
    @NotNull
    Expertise expertise,
    @Email
    String email,
    @Size(min = 6, message = "Password must be at least 6 characters long")
    String password
) {

    public Mentor toMentor(){
        return Mentor.builder()
            .name(name)
            .avatarPath(avatarPath)
            .expertise(expertise)
            .build();
    }

    public AuthUser toAuthUser(UUID mentorId){
        return AuthUser.builder()
            .email(email)
            .password(password)
            .role(Role.MENTOR)
            .referenceId(mentorId)
            .build();
    }
}
