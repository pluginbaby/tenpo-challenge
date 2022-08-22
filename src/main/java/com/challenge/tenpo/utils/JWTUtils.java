package com.challenge.tenpo.utils;

import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Component;

import java.time.Instant;

import static java.lang.String.format;
import static java.util.stream.Collectors.joining;

@Component
@AllArgsConstructor
public class JWTUtils {

    private JwtEncoder jwtEncoder;

    public String generateJWT (Authentication authentication) {
        Instant now = Instant.now();
        long expiry = 36000L;

        User user = (User) authentication.getPrincipal();

        String scope = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(joining(" "));

        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer("tenpo")
                .issuedAt(now)
                .expiresAt(now.plusSeconds(expiry))
                .subject(format("%s", user.getUsername()))
                .claim("roles", scope)
                .build();

        return this.jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
    }
}
