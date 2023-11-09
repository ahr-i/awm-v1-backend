package com.example.teamproject.JWT;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

public class JWTUtil {

    private final static SecretKey key = Keys.secretKeyFor(SignatureAlgorithm.HS256);


    public static Claims getUserName(String token){
        return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token)
                .getBody();
    }

    public static String createJwt(String userId){
        Claims claims = Jwts.claims();
        claims.put("username",userId);
        return Jwts.builder().setClaims(claims).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + (60000 * 30)))
                .signWith(key).compact();

    }
}
