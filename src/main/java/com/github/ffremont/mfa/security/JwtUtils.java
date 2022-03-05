package com.github.ffremont.mfa.security;

import com.github.ffremont.mfa.ResourceException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;

public final class JwtUtils {

    public static void check(String compactJws,String key){
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(compactJws);
        } catch (JwtException e) {
            throw new ResourceException("JWT invalide", e, 401);
        }
    }
}
