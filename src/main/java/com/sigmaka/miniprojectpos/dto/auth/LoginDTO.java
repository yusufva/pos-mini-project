package com.sigmaka.miniprojectpos.dto.auth;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginDTO {
    @NotNull(message = "email cannot be empty")
    @NotBlank(message = "email cannot be empty")
    private String email;
    @NotNull(message = "password cannot be empty")
    @NotBlank(message = "password cannot be empty")
    private String password;
}
