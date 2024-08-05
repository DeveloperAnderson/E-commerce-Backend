package com.commerce.config.contantes.auth;

import com.auth0.jwt.JWT;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.concurrent.TimeUnit;


@Component
public class JwtUtil {

    public String createToken(String username) {
        return JWT.create()
                .withSubject(username)
                .withIssuer("MiMascota")
                .withIssuedAt(new Date()).withExpiresAt(new Date(System.currentTimeMillis() + TimeUnit.MINUTES.toMillis(10)))
                .sign(TokenJwtConfig.SECRET_KEY);
    }


    public Boolean isTokenValid(String token){
        try {
            JWT.require(TokenJwtConfig.SECRET_KEY).build().verify(token); //.getExpiresAt().after(new Date());
            return true;
        }catch (JWTVerificationException jwt){
            System.out.println("Token invalido \n"+jwt.getMessage());
            return false;
        }

    }


    public String getUsernameFromToken(String token){
        return JWT.require(TokenJwtConfig.SECRET_KEY).build().verify(token).getSubject();
    }

}
