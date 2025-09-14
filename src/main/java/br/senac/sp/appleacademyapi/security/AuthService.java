package br.senac.sp.appleacademyapi.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.senac.sp.appleacademyapi.mentor.Mentor;
import br.senac.sp.appleacademyapi.mentor.MentorRepository;

@Service
public class AuthService implements UserDetailsService {

    private AuthUserRepository authUserRepository;
    private MentorRepository mentorRepository;

    public AuthService(AuthUserRepository authUserRepository, MentorRepository mentorRepository) {
        this.authUserRepository = authUserRepository;
        this.mentorRepository = mentorRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //System.out.println("auth service finding..." + username);
         AuthUser authUser = authUserRepository.findByEmail(username)
                 .orElseThrow(() -> new UsernameNotFoundException("Auth User not found"));
        
         Mentor mentor = mentorRepository.findById(authUser.getReferenceId())
                 .orElseThrow(() -> new UsernameNotFoundException("Mentor User not found"));

         authUser.setName(mentor.getName());
         authUser.setAvatarPath(mentor.getAvatarPath());

        return authUser;
    }
    
}
