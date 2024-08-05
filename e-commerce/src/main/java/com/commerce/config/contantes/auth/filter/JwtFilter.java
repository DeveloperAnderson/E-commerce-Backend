package com.commerce.config.contantes.auth.filter;

import com.commerce.config.contantes.auth.JwtUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtFilter  extends OncePerRequestFilter {


    private final JwtUtil jwtUtill;
    private final UserDetailsService userDetailsService;

    @Autowired
    public JwtFilter(JwtUtil jwtUtill,UserDetailsService userDetailsService) {
        this.jwtUtill = jwtUtill;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        //Validar que el Header sea Authorization
        String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);

        if (authHeader == null || authHeader.isEmpty() || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        //Validar que el token sea valido
        String token = authHeader.split(" ")[1].trim();

        if (!this.jwtUtill.isTokenValid(token)) {
            filterChain.doFilter(request, response);
            return;
        }

        //Cargar el usuario del UserDatilsService
        String username = this.jwtUtill.getUsernameFromToken(token);
        User user = (User) this.userDetailsService.loadUserByUsername(username);


        //cargar el usuario en el contexto de seguridad
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                user.getUsername(),
                user.getPassword(),
                user.getAuthorities()
        );

        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        System.out.println("authenticationToken : "+ authenticationToken);
        filterChain.doFilter(request, response);

    }

}
