package com.sigmaka.miniprojectpos.service;

import jakarta.transaction.Transactional;
import com.sigmaka.miniprojectpos.dto.auth.LoginDTO;
import com.sigmaka.miniprojectpos.dto.auth.LoginReponseDTO;
import com.sigmaka.miniprojectpos.dto.auth.RefreshTokenRequestDTO;
import com.sigmaka.miniprojectpos.dto.auth.SignupDTO;
import com.sigmaka.miniprojectpos.entity.RolesEntity;
import com.sigmaka.miniprojectpos.entity.UsersEntity;
import com.sigmaka.miniprojectpos.helper.GlobalHttpResponse;
import com.sigmaka.miniprojectpos.repository.RolesRepo;
import com.sigmaka.miniprojectpos.repository.UsersRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.HashMap;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UsersRepo usersRepo;
    private final RolesRepo rolesRepo;
    private final JWTUtils jwtUtils;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;


    @Transactional(rollbackOn = Exception.class)
    public GlobalHttpResponse<Object> signUp(SignupDTO signupDTO){
        try{
            UsersEntity user = signupDTO.dtoToEntity();
            RolesEntity role = rolesRepo.findById(signupDTO.getRoleId()).orElse(null);

            if (role == null){
                return new GlobalHttpResponse<>(404, "Role not Found", null);
            }

            Optional<UsersEntity> userDb = usersRepo.findByEmail(signupDTO.getEmail());
            if (userDb.isPresent()){
                return new GlobalHttpResponse<>(409, "User already exist", null);
            }

            user.setRoles(role);
            user.setPassword(passwordEncoder.encode(signupDTO.getPassword()));
            usersRepo.save(user);
            return new GlobalHttpResponse<>(201, "Successfully Create a User", null);
        }   catch (Exception e){
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return new GlobalHttpResponse<>(500, e.getMessage(), null);
        }
    }

    public GlobalHttpResponse<LoginReponseDTO> login(LoginDTO loginDTO) {
        LoginReponseDTO response;
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDTO.getEmail(), loginDTO.getPassword()));
            UsersEntity user = usersRepo.findByEmail(loginDTO.getEmail()).orElse(null);
            if (user == null){
                return new GlobalHttpResponse<>(404, "User not found", new LoginReponseDTO());
            }

            String token = jwtUtils.generateToken(user);
            String refreshToken = jwtUtils.generateRefreshToken(new HashMap<>(), user);
            response = new LoginReponseDTO();
            response.setToken(token);
            response.setRefreshToken(refreshToken);
            response.setExpirationTime("3H");
        } catch (Exception e) {
            return new GlobalHttpResponse<>(500, e.getMessage(), new LoginReponseDTO());
        }

        return new GlobalHttpResponse<>(200, "Login Success", response);
    }

    public GlobalHttpResponse<LoginReponseDTO> refreshToken(RefreshTokenRequestDTO refreshTokenRequestDTO){
        LoginReponseDTO response;
        String email = jwtUtils.extractUsername(refreshTokenRequestDTO.getToken());
        UsersEntity user = usersRepo.findByEmail(email).orElse(null);
        if (user == null){
            return new GlobalHttpResponse<>(404, "Users not found", new LoginReponseDTO());
        }

        if (jwtUtils.isTokenValid(refreshTokenRequestDTO.getToken(), user)){
            String token = jwtUtils.generateToken(user);
            response = new LoginReponseDTO();
            response.setToken(token);
            response.setRefreshToken(refreshTokenRequestDTO.getToken());
            response.setExpirationTime("3H");

            return new GlobalHttpResponse<>(200, "Successfully Refreshed Token", response);
        }

        return new GlobalHttpResponse<>(500, "Internal Server Error", new LoginReponseDTO());
    }
}
