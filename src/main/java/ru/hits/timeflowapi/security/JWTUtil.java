package ru.hits.timeflowapi.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;
import java.util.Date;
import java.util.UUID;

@Component
public class JWTUtil {

    @Value("${token.jwt-secret-key}")
    private String secret;

    @Value("${token.access-token-lifetime}")
    private Integer tokenLifetime;

    @Value("${token.issuer}")
    private String issuer;

    public String generateToken(UUID id) {
        Date issuedAt = new Date();
        Date expirationDate = Date.from(ZonedDateTime.now().plusMinutes(tokenLifetime).toInstant());

        return JWT
                .create()
                .withClaim("id", id.toString())
                .withIssuedAt(issuedAt)
                .withExpiresAt(expirationDate)
                .withIssuer(issuer)
                .sign(Algorithm.HMAC256(secret));
    }

    public UUID verifyTokenAndGetId(String token) throws JWTVerificationException {
        JWTVerifier verifier = JWT
                .require(Algorithm.HMAC256(secret))
                .withIssuer("time-flow-api")
                .build();

        DecodedJWT decodedJWT = verifier.verify(token);
        return UUID.fromString(decodedJWT
                .getClaim("id")
                .asString());
    }

}
