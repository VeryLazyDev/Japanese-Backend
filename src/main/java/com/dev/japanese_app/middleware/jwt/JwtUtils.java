package com.dev.japanese_app.middleware.jwt;

import com.dev.japanese_app.features.user.model.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.function.Function;

@Component
public class JwtUtils {

    @Value("${jwt.secret}")
    private String jwtSecret;

    public long access_token_lifetime(){
        long timeUnitInMins = 60; //60 minutes
        return timeUnitInMins * 60 * 1000L;
    }

    public long refresh_token_lifetime(){
        long timeUnitInMins = 60 * 24 * 7; // min * hour * day
        return timeUnitInMins * 60 * 1000L;
    }

    private SecretKey getSingInKey(){
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
    }

    public String extractTokenFromCookie(HttpServletRequest request){
        if (request.getCookies() == null) return  null;
        for (Cookie cookie : request.getCookies()){
            if ("access-token".equals(cookie.getName())) {
                return cookie.getValue();
            }
        }
        return null;
    }

    private Claims extractAllClaim(String token){
        return Jwts.parser()
                .verifyWith(getSingInKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    private <T> T extractClaim(String token, Function<Claims,T> claimResolver){
        return claimResolver.apply(extractAllClaim(token));
    }

    public String generateToken(User user){
        return null;
    }

    public String validToken(String token){
        return null;
    }

    private boolean isExpiredToken(String token){
        return false;
    }
}
