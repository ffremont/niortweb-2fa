package com.github.ffremont.mfa.security;

import com.github.ffremont.mfa.ResourceException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import java.nio.charset.StandardCharsets;

public final class JwtUtils {

    public static String extractSubject(String compactJws,String key){
        try {
            Jws<Claims> result = Jwts.parserBuilder().setSigningKey(Keys.hmacShaKeyFor(key.getBytes(StandardCharsets.UTF_8))).build().parseClaimsJws(compactJws);
            return result.getBody().getSubject();
        } catch (JwtException e) {
            throw new ResourceException("JWT invalide", e, 401);
        }
    }
}
