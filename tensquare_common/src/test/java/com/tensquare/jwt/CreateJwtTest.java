package com.tensquare.jwt;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

public class CreateJwtTest {

    public static void main(String[] args) {
        JwtBuilder jwtBuilder = Jwts.builder()
                .setId("111")
                .setSubject("小明")
                .setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS256, "superman");
        System.out.println(jwtBuilder.compact());
    }
}
