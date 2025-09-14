package br.senac.sp.appleacademyapi.security;

import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class AuthFilter extends OncePerRequestFilter {

    private final TokenService tokenService;

    public AuthFilter(TokenService tokenService){
        this.tokenService = tokenService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        
                var header = request.getHeader("Authorization");
                if(header == null){
                    filterChain.doFilter(request, response);
                    return;
                }

                if(!header.startsWith("Bearer")){
                    response.setStatus(401);
                    response.getWriter().write("""
                        { "message": "Header must start with Bearer" }
                    """);
                    return;
                }

                var token = header.replace("Bearer ", "");
                var user = tokenService.getUserFromToken(token);

                var authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authentication);

                filterChain.doFilter(request, response);

    }


    
}
