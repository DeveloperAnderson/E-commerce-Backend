package com.commerce.config.contantes.auth;

import com.auth0.jwt.algorithms.Algorithm;


public class TokenJwtConfig {

    private static final String KEY= "Mi_MascotaK3y";
    public static final Algorithm SECRET_KEY = Algorithm.HMAC256(KEY);

    public static final String PREFIX_TOKEN = "Bearer";
    public static final String HEADER_AUTHORIZATION = "Authorization";
    public static final String CONTENT_TYPE = "application/json";


    public static final long VALIDITY_TOKEN = 3600000L; // 1 hora

}
