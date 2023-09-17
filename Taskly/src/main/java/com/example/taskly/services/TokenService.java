package com.example.taskly.services;

import java.time.Instant;
import java.util.Date;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;
import org.springframework.security.oauth2.jwt.Jwt;

import com.example.taskly.models.ApplicationUser;
import com.example.taskly.models.UserProperties;
import com.nimbusds.jwt.JWTClaimsSet;

@Service
public class TokenService {

	private final JwtEncoder jwtEncoder;
	private final JwtDecoder jwtDecoder;

	@Autowired
	public TokenService(JwtEncoder jwtEncoder, JwtDecoder jwtDecoder) {
		this.jwtEncoder = jwtEncoder;
		this.jwtDecoder = jwtDecoder;
	}

	public String generateJwt(Authentication authrization) {

		Instant now = Instant.now();
		long expiryTime = 360000;
		Instant expiryInsatnt = now.plusMillis(expiryTime);

		String scope = authrization.getAuthorities().stream().map(GrantedAuthority::getAuthority)
				.collect(Collectors.joining(" "));

		ApplicationUser details = (ApplicationUser) authrization.getPrincipal();
		Long userId = details.getUserId();

		JwtClaimsSet claims = JwtClaimsSet.builder().issuer("self").issuedAt(now).expiresAt(expiryInsatnt).subject(authrization.getName())
				.claim("id", userId).claim("roles", scope).build();

		return jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
	}

	public UserProperties decodeJwt(String token) {
	
		Jwt jwt = jwtDecoder.decode(token);
		String subject = jwt.getSubject();
		Long userId = jwt.getClaim("id");
		String role = jwt.getClaim("roles");
		return new UserProperties(userId, subject, role); 
	}
}
