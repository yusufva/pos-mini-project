package com.sigmaka.miniprojectpos.controller;

import com.sigmaka.miniprojectpos.dto.auth.LoginDTO;
import com.sigmaka.miniprojectpos.dto.auth.LoginReponseDTO;
import com.sigmaka.miniprojectpos.dto.auth.RefreshTokenRequestDTO;
import com.sigmaka.miniprojectpos.dto.auth.SignupDTO;
import com.sigmaka.miniprojectpos.helper.GlobalHttpResponse;
import com.sigmaka.miniprojectpos.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth/")
@RequiredArgsConstructor
@Validated
public class AuthController {
    private final AuthService authService;

    @PostMapping("signup")
    public ResponseEntity<GlobalHttpResponse<Object>> signup(@Valid @RequestBody SignupDTO signupDTO){
        GlobalHttpResponse<Object> res = authService.signUp(signupDTO);
        return new ResponseEntity<>(res, HttpStatusCode.valueOf(res.getStatusCode()));
    }
    @PostMapping("login")
    public ResponseEntity<GlobalHttpResponse<LoginReponseDTO>> login(@Valid @RequestBody LoginDTO loginDTO){
        GlobalHttpResponse<LoginReponseDTO> res = authService.login(loginDTO);
        return new ResponseEntity<>(res, HttpStatusCode.valueOf(res.getStatusCode()));
    }

    @PostMapping("refresh")
    public ResponseEntity<GlobalHttpResponse<LoginReponseDTO>> refresh(@Valid @RequestBody RefreshTokenRequestDTO refreshTokenRequestDTO){
        GlobalHttpResponse<LoginReponseDTO> res = authService.refreshToken(refreshTokenRequestDTO);
        return new ResponseEntity<>(res, HttpStatusCode.valueOf(res.getStatusCode()));
    }
}
