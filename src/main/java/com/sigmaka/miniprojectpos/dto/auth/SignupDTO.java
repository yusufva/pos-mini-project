package com.sigmaka.miniprojectpos.dto.auth;

import com.sigmaka.miniprojectpos.entity.RolesEntity;
import com.sigmaka.miniprojectpos.entity.UsersEntity;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignupDTO {
    @NotNull(message = "email cannot be null")
    @NotBlank(message = "email cannot be blank")
    @Email(message = "invalid email format")
    @Size(max = 255, message = "email maximum length is 255")
    private String email;
    @NotNull(message = "username cannot be empty")
    @NotBlank(message = "username cannot be blank")
    @Size(min = 4, max = 255, message = "username length minimum character is 4 and maximum character is 255")
    private String username;
    @NotNull(message = "password cannot be null")
    @NotBlank(message = "password cannot be blank")
    @Size(min = 8, max = 70, message = "password minimum length is 8 and maximum length is 70")
    private String password;
    @NotNull(message = "role id cannot be empty")
    private int roleId;

    public UsersEntity dtoToEntity(){
        UsersEntity user = new UsersEntity();
        RolesEntity roles = new RolesEntity();
        roles.setId(roleId);

        user.setEmail(this.email);
        user.setUsername(this.username);
        user.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        user.setUpdatedAt(new Timestamp(System.currentTimeMillis()));

        return user;
    }
}
