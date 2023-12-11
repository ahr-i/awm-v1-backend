package com.example.teamproject.JWT;

import com.example.teamproject.Dto.UserDto.UserDto;
import com.example.teamproject.Service.SpringSecurityLogin.PrincipalDetails;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import lombok.extern.slf4j.Slf4j;

import javax.crypto.SecretKey;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
@Slf4j

public class JWTUtil {

    private final static SecretKey key = Keys.secretKeyFor(SignatureAlgorithm.HS256);


    public static Claims getUserName(String token, HttpServletResponse response) {
        try {
            return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token)
                    .getBody();
        } catch (SignatureException e) {
            log.info("에러 {}",e);
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        }
        return null;
    }

    public static String createJwt(UserDto dto){
        Claims claims = Jwts.claims();
        claims.put("username",dto.getUserId());
        claims.put("provider",dto.getProvider());
        claims.put("nickName",dto.getNickName());
        claims.put("rankScore",dto.getRankScore());
        return Jwts.builder().setClaims(claims).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + (60000 * 30 * 24)))
                .signWith(key).compact();
    }
    public static String createOauthJwt(PrincipalDetails user){
        long expireTime = 60000 * 30 * 24;
        Claims claims = Jwts.claims();
        claims.put("username",user.getUserInfo().getUserId());
        claims.put("provider",user.getUserInfo().getProvider());
        claims.put("nickName",user.getUserInfo().getNickName());
        claims.put("rankScore",user.getUserInfo().getRankScore());
       return Jwts.builder()
               .setClaims(claims).setIssuedAt(new Date(System.currentTimeMillis()))
               .setExpiration(new Date(System.currentTimeMillis() + expireTime ))
               .signWith(key).compact();
    }
}
