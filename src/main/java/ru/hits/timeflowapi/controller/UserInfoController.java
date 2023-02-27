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
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
@Tag(name = "Личный кабинет")
public class UserInfoController {

    private final UserInfoService userInfoService;
    private final CheckEmailService checkEmailService;

    private UUID extractId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return UUID.fromString(authentication.getName());
    }

    @GetMapping
    UserDto getUserInfo() {
        UUID id = extractId();
        return userInfoService.getUserInfo(id);
    }

    @PutMapping("/email")
    UserDto updateEmail(@Valid @RequestBody EditEmailDto editEmailDto) {
        checkEmailService.checkEmail(editEmailDto.getEmail());
        UUID id = extractId();

        return userInfoService.updateEmail(id, editEmailDto);
    }

    @PutMapping("/password")
    UserDto updatePassword(@Valid @RequestBody EditPasswordDto editPasswordDto) {
        UUID id = extractId();

        return userInfoService.updatePassword(id, editPasswordDto);
    }

}
