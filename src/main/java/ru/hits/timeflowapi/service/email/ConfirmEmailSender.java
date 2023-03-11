package ru.hits.timeflowapi.service.email;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import ru.hits.timeflowapi.entity.UserEntity;
import ru.hits.timeflowapi.service.StringCryptographer;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

@Service
@RequiredArgsConstructor
@Slf4j
public class ConfirmEmailSender {

    @Value("${spring.mail.username}")
    private String username;
    @Value("${email_confirm.port}")
    private String port;
    @Value("${email_confirm.host}")
    private String host;
    private final JavaMailSender javaMailSender;
    private final StringCryptographer cryptographer;

    public void sendConfirmEmail(UserEntity user) {
        LocalDateTime expDateTime = LocalDateTime
                .ofInstant(Instant.now(), ZoneId.systemDefault())
                .plusDays(1);

        String payload = user.getId() + "/" + user.getEmail() + "/" + expDateTime;
        String url = "http://" + host + ":" + port +
                "/api/v1/confirm-email/?payload=" + cryptographer.encryptString(payload);

        sendEmail(user.getEmail(), "Подтверждение почты", url);
    }

    private void sendEmail(String toAddress, String subject, String message) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom(username);
        simpleMailMessage.setTo(toAddress);
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setText(message);

        javaMailSender.send(simpleMailMessage);
    }

}