package com.tensquare.jwt;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

public class CreateJwtTest3 {
    public static void main(String[] args) {
        JwtBuilder builder = Jwts.builder().setIssuedAt(new Date())
                .setId("3333")
                .setSubject("小宝")
                .claim("name", "大笑着")
                .claim("state", 1)
                .signWith(SignatureAlgorithm.HS256,"superman");
        System.out.println(builder.compact());

    }
}
