package org.fastcampus.community_feed.auth.domain;

import static io.jsonwebtoken.security.Keys.*;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import java.util.Date;
import javax.crypto.SecretKey;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class TokenProvider {
  private final SecretKey key;
  private static final long TOKEN_VALID_TIME = 1000L * 60 * 60; // 1시간

  public TokenProvider(@Value("${secret-key}") String secretKey) {
    this.key = hmacShaKeyFor(secretKey.getBytes());
  }

  public String createToken(Long userId, String role) {
    Claims claims = Jwts.claims()
        .subject(userId.toString())
        .build();
    Date now = new Date();
    Date validity = new Date(now.getTime() + TOKEN_VALID_TIME);

    return Jwts.builder()
        .claims(claims)
        .issuedAt(now)
        .expiration(validity)
        .claim("role", role)
        .signWith(key)
        .compact();
  }

  public Long getUserId(String token) {
    return Long.parseLong(
        Jwts.parser()
            .verifyWith(key)
            .build()
            .parseSignedClaims(token)
            .getPayload()
            .getSubject()
    );
  }

  public String getRoles(String token) {
    return Jwts.parser()
        .verifyWith(key)
        .build()
        .parseSignedClaims(token)
        .getPayload()
        .get("role", String.class);
  }

}
