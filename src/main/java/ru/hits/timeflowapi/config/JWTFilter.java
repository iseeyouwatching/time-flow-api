package ru.hits.timeflowapi.config;

import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import ru.hits.timeflowapi.dto.ApiError;
import ru.hits.timeflowapi.exception.AccessTokenNotValidException;
import ru.hits.timeflowapi.exception.RefreshTokenNotValidException;
import ru.hits.timeflowapi.exception.UnauthorizedException;
import ru.hits.timeflowapi.security.JWTService;
import ru.hits.timeflowapi.security.UserDetailsServiceImpl;
import ru.hits.timeflowapi.util.constants.SecuredEndpoints;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.UUID;

@Component
@RequiredArgsConstructor
@Slf4j
public class JWTFilter extends OncePerRequestFilter {

    private final JWTService jwtService;
    private final UserDetailsServiceImpl userDetailsService;

    /**
     * Проверка валидности токена. Этот фильтр вызывается для каждого запроса. Цепочку нужно прервать
     * если в запросе указали токен, и он не является валидным.
     *
     * @param request     запрос.
     * @param response    ответ.
     * @param filterChain объект для вызова следующего фильтра.
     * @throws ServletException возникает, если при работе сервлетов что-то пошло не так.
     * @throws IOException      возникает, при записи информации в тело ответа.
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain
    ) throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization");

        if (authHeader != null) {
            try {
                String jwt = authHeader.substring(7);
                UUID id = jwtService.verifyAccessTokenAndGetId(jwt);
                UserDetails userDetails = userDetailsService.loadUserByUsername(id.toString());

                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        userDetails,
                        userDetails.getPassword(),
                        userDetails.getAuthorities()
                );

                if (SecurityContextHolder.getContext().getAuthentication() == null) {
                    SecurityContextHolder.getContext().setAuthentication(authToken);
                }
            } catch (AccessTokenNotValidException exception) {
                log.error(exception.getMessage(), exception);
                sendError(response, 450, exception.getMessage());
                return;
            } catch (RefreshTokenNotValidException exception) {
                log.error(exception.getMessage(), exception);
                sendError(response, 451, exception.getMessage());
                return;
            } catch (UnauthorizedException exception) {
                log.error(exception.getMessage(), exception);
                sendError(response, 401, exception.getMessage());
                return;
            }

        } else {
            log.error("Отсутствует заголовок \"Authorization\" в хэдере запроса на защищенном эндпоинте. " +
                    "Статус код: 450");
            sendError(response, 450, "Отсутствует access токен.");
            return;
        }

        filterChain.doFilter(request, response);
    }

    /**
     * Метод, который определяет необходимость выполнения текущего фильтра. Если метод возвращает {@code true},
     * то фильтр не выполняется, если {@code false}, то фильтр выполняется.
     *
     * @param request текущий {@code HTTP} запрос.
     * @return переменную типа {@code boolean}, которая говорит <strong>нужно ли не выполнять</strong> этот фильтр.
     */
    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        for (AntPathRequestMatcher endpoint : SecuredEndpoints.ENDPOINTS) {
            if (endpoint.matcher(request).isMatch()) {
                return false;
            }
        }

        return true;
    }

    /**
     * Метод для отправки ответа на запрос на уровне {@link HttpServletResponse}.
     *
     * @param response   ответ на запрос, который изменится.
     * @param statusCode статус код ответа.
     * @param message    текст ошибки.
     * @throws IOException может возникнуть при записи информации в тело ответа.
     */
    private void sendError(HttpServletResponse response,
                           Integer statusCode,
                           String message
    ) throws IOException {
        ApiError error = new ApiError(message);
        String responseBody = new Gson().toJson(error);

        response.setStatus(statusCode);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        PrintWriter out = response.getWriter();
        out.print(responseBody);
        out.flush();
    }

}
