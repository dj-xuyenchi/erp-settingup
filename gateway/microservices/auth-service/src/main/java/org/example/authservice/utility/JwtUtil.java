package org.example.authservice.utility;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Encoders;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.example.authservice.dto.UserVO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtUtil {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private String expirationTime;

    private Key key;

    @PostConstruct
    public void init() {
        this.key = Keys.hmacShaKeyFor(secret.getBytes());
    }

    public Claims getClaimsFromToken(String token) {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }


    public Date getExpirationDateFromToken(String token) {
        return getClaimsFromToken(token).getExpiration();
    }

    private Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    ///ACCESS là access token
    ///REFRESH là refresh token có hiệu lực gấp 5 lần access
    public String generate(UserVO userVO, String type) {
        Map<String, Object> claims = new HashMap<>();
        claims.put(Claims.ID, userVO.getId());
        claims.put(Claims.SUBJECT, userVO.getEmail());
        claims.put("roles", userVO.getRole());
        long expirationTimeLong = 0;
        if ("ACCESS".equals(type)) {
            // 24h = 1 ngày
            expirationTimeLong =Long.parseLong(expirationTime) * 1000 ;
        }
        if ("REFRESH".equals(type)) {
            //24h * 5 = 5 ngày
            expirationTimeLong =Long.parseLong(expirationTime) * 1000 *5;
        }
        final Date createdDate = new Date();
        final Date expirationDate = new Date(createdDate.getTime() + expirationTimeLong);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(createdDate)
                .setExpiration(expirationDate)
                .signWith(key)
                .compact();
    }


    public Boolean validateToken(String token) {
        return !isTokenExpired(token);
    }

    public static void main(String[] args) {
        Key key = Keys.secretKeyFor(SignatureAlgorithm.HS512);
        String secretString = Encoders.BASE64.encode(key.getEncoded());
        System.out.println(secretString);
    }
}

