package br.senac.sp.appleacademyapi.student;

import br.senac.sp.appleacademyapi.mentor.Expertise;
import br.senac.sp.appleacademyapi.security.AuthUser;

public record StudentResponse(
    String id,
    String name,
    String avatarPath,
    Expertise expertise,
    DeliveryMode deliveryMode,
    String email
) {

    public static StudentResponse from(Student student, AuthUser authUser) {
        return new StudentResponse(
            student.getId().toString(),
            student.getName(),
            student.getAvatarPath(),
            student.getExpertise(),
            student.getDeliveryMode(),
            authUser.getEmail()
        );
    }

}
