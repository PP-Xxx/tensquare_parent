package com.tensquare.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

import java.text.SimpleDateFormat;

public class ParseJwtTest {
    public static void main(String[] args) {
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE1NjM3ODQxMTQsImp0aSI6IjMzMzMiLCJzdWIiOiLlsI_lrp0iLCJuYW1lIjoi5aSn56yR552AIiwic3RhdGUiOjF9.KQYKyQoKNGq_QzqA4yo7xNSiCyAWLFMAxt7OAkrXfmA";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Claims claims = Jwts.parser()
                .setSigningKey("superman")
                .parseClaimsJws(token)
                .getBody();
        System.out.println("用户id："+claims.getId());
        System.out.println("用户名："+claims.getSubject());
//        System.out.println("过期时间:"+sdf.format(claims.getExpiration()));
        System.out.println("登陆时间："+sdf.format(claims.getIssuedAt()));
        System.out.println("自定义name："+claims.get("name"));
        System.out.println("自定义state："+claims.get("state"));
    }
}
