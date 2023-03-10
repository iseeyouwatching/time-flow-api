package ru.hits.timeflowapi.util.constants;

import lombok.experimental.UtilityClass;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Класс с паттернами для тех эндпоинтов, которые являются защищенными.
 */
@UtilityClass
public class SecuredEndpoints {

    private static final String BASE_URL = "/api/v1";

    public static final List<AntPathRequestMatcher> ENDPOINTS = new ArrayList<>(Arrays.asList(
            new AntPathRequestMatcher(BASE_URL + "/student-requests/**"),
            new AntPathRequestMatcher(BASE_URL + "/employee-requests/**"),
            new AntPathRequestMatcher(BASE_URL + "/schedule-maker-requests/**"),
            new AntPathRequestMatcher(BASE_URL + "/available-timeslots"),
            new AntPathRequestMatcher(BASE_URL + "/available-teachers"),
            new AntPathRequestMatcher(BASE_URL + "/available-classrooms"),
            new AntPathRequestMatcher(BASE_URL + "/account/**"),
            new AntPathRequestMatcher(BASE_URL + "/sign-out"),
            new AntPathRequestMatcher(BASE_URL + "/users"),
            new AntPathRequestMatcher(BASE_URL + "/students"),
            new AntPathRequestMatcher(BASE_URL + "/employees"),
            new AntPathRequestMatcher(BASE_URL + "/employee-posts"),
            new AntPathRequestMatcher(BASE_URL + "/lessons/**", HttpMethodConstant.POST),
            new AntPathRequestMatcher(BASE_URL + "/lessons/**", HttpMethodConstant.PUT),
            new AntPathRequestMatcher(BASE_URL + "/lessons/**", HttpMethodConstant.DELETE)
    ));

}
