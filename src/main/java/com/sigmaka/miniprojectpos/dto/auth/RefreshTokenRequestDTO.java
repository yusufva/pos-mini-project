package com.sigmaka.miniprojectpos.dto.auth;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RefreshTokenRequestDTO {
    @NotNull(message = "token cannot be empty")
    @NotBlank(message = "token cannot be empty")
    private String token;
}
