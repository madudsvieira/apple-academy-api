package br.senac.sp.appleacademyapi.security;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;


@Service
public class TokenService {

    private final Algorithm algorithm;
    
    public TokenService(@Value("${jwt.secret}") String secret) {
        this.algorithm = Algorithm.HMAC256(secret);
    }

    public String generateToken(AuthUser authUser) {
        return JWT.create()
                .withSubject(authUser.getId().toString())
                .withClaim("email", authUser.getEmail())
                .withClaim("role", authUser.getRole().name())
                .sign(algorithm);
    }

    public AuthUser getUserFromToken(String token) {
        var jwt = JWT.require(algorithm).build().verify(token);
        return AuthUser.builder()
                .id(UUID.fromString(jwt.getSubject()))
                .email(jwt.getClaim("email").toString())
                .role(Role.valueOf(jwt.getClaim("role").asString()))
                .build();
    }
    
}
