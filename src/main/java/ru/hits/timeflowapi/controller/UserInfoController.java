package ru.hits.timeflowapi.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.hits.timeflowapi.model.dto.user.EditEmailDto;
import ru.hits.timeflowapi.model.dto.user.EditPasswordDto;
import ru.hits.timeflowapi.model.dto.user.UserDto;
import ru.hits.timeflowapi.service.UserInfoService;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserInfoController {

    private final UserInfoService userInfoService;

    String GetUserEmail() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName();
    }

    @GetMapping
    UserDto getUserInfo() {
        String email = GetUserEmail();
        return userInfoService.getUserInfo(email);
    }

    @PutMapping("/email")
    UserDto PutEmail(EditEmailDto editEmailDto) {
        String email = GetUserEmail();
        return userInfoService.PutEmail(email, editEmailDto);
    }

    @PutMapping("/password")
    UserDto PutPassword(EditPasswordDto editPasswordDto) {
        String email = GetUserEmail();
        return userInfoService.PutPassword(email, editPasswordDto);
    }

}
