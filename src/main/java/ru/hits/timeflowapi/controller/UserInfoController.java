package ru.hits.timeflowapi.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import ru.hits.timeflowapi.model.dto.user.EditEmailDto;
import ru.hits.timeflowapi.model.dto.user.EditPasswordDto;
import ru.hits.timeflowapi.model.dto.user.UserDto;
import ru.hits.timeflowapi.service.CheckEmailService;
import ru.hits.timeflowapi.service.UserInfoService;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
@Tag(name = "Личный кабинет")
public class UserInfoController {

    private final UserInfoService userInfoService;
    private final CheckEmailService checkEmailService;

    private String GetUserEmail() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName();
    }

    @GetMapping
    UserDto getUserInfo() {
        String email = GetUserEmail();
        return userInfoService.getUserInfo(email);
    }

    @PutMapping("/email")
    UserDto PutEmail(@Valid @RequestBody EditEmailDto editEmailDto) {

        checkEmailService.checkEmail(editEmailDto.getEmail());
        String email = GetUserEmail();
        return userInfoService.PutEmail(email, editEmailDto);
    }

    @PutMapping("/password")
    UserDto PutPassword(@Valid @RequestBody EditPasswordDto editPasswordDto) {
        String email = GetUserEmail();
        return userInfoService.PutPassword(email, editPasswordDto);
    }

}
