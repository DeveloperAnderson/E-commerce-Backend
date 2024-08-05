package com.commerce.controller;


import com.commerce.DTO.AuthResponseDto;
import com.commerce.DTO.LoginDto;
import com.commerce.config.contantes.auth.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import static com.commerce.config.contantes.ApiVersion.CON_API;

@RestController
@RequestMapping(CON_API+"/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;


    @Autowired
    public AuthController(AuthenticationManager authenticationManager, JwtUtil jwtUtil) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/log-in")
    public AuthResponseDto login(@RequestBody LoginDto loginDto){
        System.out.println("Logeando...");
        System.out.println("Logeando...");
        System.out.println("Logeando...");

        System.out.println("Username: "+loginDto.getUsername());
        System.out.println("Password: "+loginDto.getPassword());

        String username = loginDto.getUsername();
        String password = loginDto.getPassword();

        UsernamePasswordAuthenticationToken login = new UsernamePasswordAuthenticationToken(username, password);
        Authentication authentication = this.authenticationManager.authenticate(login);

        System.out.printf("Usuario autenticado: %s\n", authentication.isAuthenticated());
        System.out.println("Usuario: "+authentication.getPrincipal());

        String jwt = this.jwtUtil.createToken(username);

        AuthResponseDto authResponse = new AuthResponseDto(username, "User loged succesfully", jwt, true);
        return authResponse;

        //return ResponseEntity.ok().header(HttpHeaders.AUTHORIZATION, jwt).build();
    }

}
