package br.senac.sp.appleacademyapi.security;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    record LoginRequest(String email, String password) {}
    record LoginResponse(String token, String name, String avatarPath, String email, String id) {}

    private final TokenService tokenService;
    private final AuthenticationManager authenticationManager;;

    public AuthController(TokenService tokenService, AuthenticationManager authenticationManager) {
        this.tokenService = tokenService;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest request){
        Authentication authentication = new UsernamePasswordAuthenticationToken(request.email(), request.password());
        AuthUser authUser = (AuthUser) authenticationManager.authenticate(authentication).getPrincipal();

        String token = tokenService.generateToken(authUser);
        return new LoginResponse(
            token, 
            authUser.getName(), 
            authUser.getAvatarPath(),
            authUser.getEmail(),
            authUser.getId().toString()
            );
    }
    
}
