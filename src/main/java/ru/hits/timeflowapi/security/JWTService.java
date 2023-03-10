package ru.hits.timeflowapi.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.hits.timeflowapi.dto.signin.TokensDto;
import ru.hits.timeflowapi.entity.UserEntity;
import ru.hits.timeflowapi.exception.AccessTokenNotValidException;
import ru.hits.timeflowapi.exception.RefreshTokenNotValidException;
import ru.hits.timeflowapi.exception.UnauthorizedException;
import ru.hits.timeflowapi.repository.UserRepository;

import java.time.ZonedDateTime;
import java.util.Date;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class JWTService {

    private final UserRepository userRepository;

    @Value("${token.access.secret-key}")
    private String accessSecret;

    @Value("${token.refresh.secret-key}")
    private String refreshSecret;

    @Value("${token.access.lifetime-min}")
    private Integer accessLifeTime;

    @Value("${token.refresh.lifetime-min}")
    private Integer refreshLifeTime;

    @Value("${token.issuer}")
    private String issuer;

    private static final String UNAUTHORIZED_MESSAGE = "Не авторизован.";

    /**
     * Метод для генерации {@code access} токена.
     *
     * @param id {@code id} пользователя.
     * @return {@code access} токена.
     */
    public String generateAccessToken(UUID id) {
        return generateToken(id, accessSecret, accessLifeTime);
    }

    /**
     * Метод для верификации {@code access} токена и получения из него {@code id} пользователя.
     *
     * @param token {@code access} токен
     * @return {@code id}  пользователя из полезной нагрузки токена.
     * @throws AccessTokenNotValidException возникает, если токен невалиден.
     */
    public UUID verifyAccessTokenAndGetId(String token) throws UnauthorizedException {
        try {
            return verifyAndExtractId(token, accessSecret);
        } catch (JWTVerificationException exception) {
            log.error("Исключение во время верификации access токена.", exception);

            throw new AccessTokenNotValidException("Невалидный access токен.");
        }
    }

    /**
     * Метод для обновления пары токенов.
     *
     * @param refreshToken {@code refresh} токен.
     * @return пара {@code access} и {@code refresh} токенов.
     * @throws RefreshTokenNotValidException возникает, если refresh токен невалиден.
     */
    public TokensDto updateTokens(String refreshToken) throws UnauthorizedException {
        try {
            UUID id = verifyAndExtractId(refreshToken, refreshSecret);

            if (!userRepository.existsByIdAndRefreshToken(id, refreshToken)) {
                throw new RefreshTokenNotValidException("Невалидный refresh токен.");
            }

            return generateTokens(id);
        } catch (JWTVerificationException exception) {
            log.error("Ошибка во время верификации refresh токена", exception);
            throw new RefreshTokenNotValidException("Невалидный refresh токен.");
        }
    }

    /**
     * Метод для генерации пары {@code refresh} {@code access} токена.
     *
     * @param userID {@code id} пользователя.
     * @return пара {@code access} и {@code refresh} токенов.
     * @throws UnauthorizedException возникает, если токен подделан, или пользователь
     *                               не найден по {@code ID} из полезной нагрузки токена.
     */
    public TokensDto generateTokens(UUID userID) throws UnauthorizedException {
        UserEntity user = userRepository.findById(userID).orElseThrow(() -> {
            throw new UnauthorizedException(UNAUTHORIZED_MESSAGE);
        });

        String newRefreshToken = generateToken(userID, refreshSecret, refreshLifeTime);

        user.setRefreshToken(newRefreshToken);
        user = userRepository.save(user);

        return new TokensDto(
                generateAccessToken(userID),
                user.getRefreshToken(),
                Date.from(ZonedDateTime.now().plusMinutes(accessLifeTime).toInstant())
        );
    }

    /**
     * Метод для генерации токена.
     *
     * @param userId   {@code id} пользователя.
     * @param secret   секретный ключ для шифрования токена.
     * @param lifeTime время жизни токена.
     * @return токен.
     */
    private String generateToken(UUID userId, String secret, Integer lifeTime) {
        Date issuedAt = new Date();
        Date expirationDate = Date.from(ZonedDateTime.now().plusMinutes(lifeTime).toInstant());

        return JWT
                .create()
                .withClaim("id", userId.toString())
                .withIssuedAt(issuedAt)
                .withExpiresAt(expirationDate)
                .withIssuer(issuer)
                .sign(Algorithm.HMAC256(secret));
    }

    /**
     * Метод для верификации токена и извлечению {@code ID} пользователя из него.
     *
     * @param token  {@code access} или {@code refresh} токен.
     * @param secret секретный ключ, с помощью которого токен был закодирован.
     * @return id пользователя.
     * @throws JWTVerificationException возникает, если токен был подделан, истёк, пуст.
     */
    private UUID verifyAndExtractId(String token, String secret) throws JWTVerificationException {
        if (token == null || token.isBlank()) {
            throw new JWTVerificationException("Токен пустой");
        }

        JWTVerifier verifier = JWT
                .require(Algorithm.HMAC256(secret))
                .withIssuer(issuer)
                .build();

        DecodedJWT decodedJWT = verifier.verify(token);

        return UUID.fromString(decodedJWT
                .getClaim("id")
                .asString()
        );

    }

}
