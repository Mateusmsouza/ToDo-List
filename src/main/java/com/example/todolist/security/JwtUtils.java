package com.example.todolist.security;

import com.example.todolist.model.user.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

public class JwtUtils {

    private static final String superSecretKey = "some_really_secret_key";

    public static String generateToken(User user) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        String userAsString = mapper.writeValueAsString(user);
        Date now = new Date();
        Long hour = 1000L * 60L * 60L;

        return Jwts.builder().claim("userDetails", userAsString)
                .setIssuer("com.example.todolist")
                .setSubject(user.getName())
                .setExpiration(new Date(now.getTime() + hour))
                .signWith(SignatureAlgorithm.HS512, superSecretKey)
                .compact();
    }

    public static User decryptToken(String token) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        String credentials = Jwts.parser()
                .setSigningKey(superSecretKey)
                .parseClaimsJws(token)
                .getBody()
                .get("userDetails", String.class);

        return mapper.readValue(credentials, User.class);
    }
}
