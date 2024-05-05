package com.example.emailgaxabaryuborish.Token;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class Tokenn {
    String kalit = "qkgi@46$DSA$%^543";
    public  String tokenOlish(String username){
        long vaqt = 36_00_00;
        Date date = new Date(System.currentTimeMillis()+vaqt);

        String token = Jwts
                .builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(date)
                .signWith(SignatureAlgorithm.HS512, kalit)
                .compact();
        return token;
    }

    public boolean check(String token){
        try {
            Jwts
                    .parser()
                    .setSigningKey(kalit)
                    .parseClaimsJws(token);
            return true;
        }
        catch (Exception e){
            return false;
        }
    }

    public String deshifr(String auth){
        String username = Jwts
                .parser()
                .setSigningKey(kalit)
                .parseClaimsJws(auth)
                .getBody()
                .getSubject();
        return username;
    }
}
