package com.example.teamproject.JWT;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.nio.charset.StandardCharsets;
import java.util.Date;

public class JWTUtil {

    public static boolean isExpired(String token,String secretKey){
        byte[] keyBytes = secretKey.getBytes(StandardCharsets.UTF_8);
        return Jwts.parser().setSigningKey(keyBytes).parseClaimsJws(token).getBody().getExpiration().before(new Date());
    }
    public static String getUserName(String token,String secretKey){
        byte[] keyBytes = secretKey.getBytes(StandardCharsets.UTF_8);
      return   Jwts.parser().setSigningKey(keyBytes).parseClaimsJws(token)
                .getBody().get("username",String.class);
    }

    public static String createJwt(String userId,String secretKey){
        Claims claims = Jwts.claims();
        byte[] keyBytes = secretKey.getBytes(StandardCharsets.UTF_8);
        claims.put("username",userId);
        return Jwts.builder().setClaims(claims).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + (60000 * 30)))
                .signWith(SignatureAlgorithm.HS256,keyBytes).compact();

    }
}
