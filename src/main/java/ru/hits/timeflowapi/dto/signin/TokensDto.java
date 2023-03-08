package ru.hits.timeflowapi.dto.signin;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class TokensDto {

    private String accessToken;

    private String refreshToken;

    private Date expirationDate;

}
