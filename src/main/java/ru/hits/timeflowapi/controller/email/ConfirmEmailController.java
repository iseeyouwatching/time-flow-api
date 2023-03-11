package ru.hits.timeflowapi.controller.email;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.hits.timeflowapi.dto.user.UserDto;
import ru.hits.timeflowapi.exception.EmailAlreadyConfirmedException;
import ru.hits.timeflowapi.exception.LinkExpiredException;
import ru.hits.timeflowapi.service.email.ConfirmEmailService;

@Controller
@RequestMapping("/api/v1")
@RequiredArgsConstructor
@Slf4j
public class ConfirmEmailController {

    private final ConfirmEmailService confirmEmailService;

    @GetMapping("/confirm-email")
    public String confirmEmail(Model model, @RequestParam String payload) {
        try {
            UserDto userDto = confirmEmailService.confirmEmail(payload);
            model.addAttribute("name", userDto.getName());
            model.addAttribute("surname", userDto.getSurname());
            return "email_confirm_success";
        } catch (EmailAlreadyConfirmedException exception) {
            log.error(exception.getMessage(), exception);
            return "email_already_confirmed";
        } catch (LinkExpiredException exception) {
            log.error(exception.getMessage(), exception);
            return "link_expired";
        } catch (Exception exception) {
            log.error(exception.getMessage(), exception);
            return "invalid_link";
        }
    }

}
