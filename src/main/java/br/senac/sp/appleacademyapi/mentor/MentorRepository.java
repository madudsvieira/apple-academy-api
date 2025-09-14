package br.senac.sp.appleacademyapi.mentor;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MentorRepository extends JpaRepository<Mentor, UUID> {

    
}
