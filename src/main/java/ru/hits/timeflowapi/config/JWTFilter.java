package ru.hits.timeflowapi.config;

import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import ru.hits.timeflowapi.exception.UnauthorizedException;
import ru.hits.timeflowapi.dto.ApiError;
import ru.hits.timeflowapi.security.JWTService;
import ru.hits.timeflowapi.security.UserDetailsServiceImpl;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.UUID;

@Component
@RequiredArgsConstructor
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
            } catch (UnauthorizedException | StringIndexOutOfBoundsException exception) {
                sendUnauthorizedError(response);
                return;
            }
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
     * Отправляет в ответ на запрос ошибку о том, что пользователь не авторизован.
     *
     * @param response ответ на запрос, который изменится.
     * @throws IOException может возникнуть при записи информации в тело ответа.
     */
    private void sendUnauthorizedError(HttpServletResponse response) throws IOException {
        ApiError error = new ApiError("Не авторизован.");
        String responseBody = new Gson().toJson(error);

        response.setStatus(401);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        PrintWriter out = response.getWriter();
        out.print(responseBody);
        out.flush();
    }

}
