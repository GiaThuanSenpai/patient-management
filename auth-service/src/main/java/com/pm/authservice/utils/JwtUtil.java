package com.pm.authservice.utils;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.security.SignatureException;
import java.util.Base64;
import java.util.Date;

import javax.crypto.SecretKey;

import org.hibernate.annotations.Comment;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;


@Component
public class JwtUtil {

	private final Key secretKey;
	
	public static String SECRET_KEY = "dsadasdhasuidhuasdyuiasydiuasasdasd";

	
	public JwtUtil(@Value("${jwt.secret}") String secret) {
		this.secretKey = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
	}
	public String generateToken(String email, String role) {
		return Jwts.builder().subject(email)
				.claim("role", role)
				.issuedAt(new Date())
				.expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
				.signWith(secretKey)
				.compact();
	}
	public void validateToken(String token) {
		try {
			Jwts.parser().verifyWith((SecretKey) secretKey)
				.build()
				.parseSignedClaims(token);
		} catch (JwtException e) {
			throw new JwtException("Invalid JWT");
		}
	}
}
