package br.senac.sp.appleacademyapi.mentor;

import java.util.UUID;

import br.senac.sp.appleacademyapi.security.AuthUser;

public record MentorResponse(
    UUID id,
    String name,
    String avatarPath,
    Expertise expertise,
    String email
) {

    public static MentorResponse from(Mentor mentor, AuthUser authUser) {
        return new MentorResponse(
            mentor.getId(),
            mentor.getName(),
            mentor.getAvatarPath(),
            mentor.getExpertise(),
            authUser.getEmail()
        );
        
    }}
