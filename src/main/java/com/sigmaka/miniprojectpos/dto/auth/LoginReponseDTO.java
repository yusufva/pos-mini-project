package com.sigmaka.miniprojectpos.dto.auth;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class LoginReponseDTO {
    private String token;
    private String refreshToken;
    private String  expirationTime;
}
