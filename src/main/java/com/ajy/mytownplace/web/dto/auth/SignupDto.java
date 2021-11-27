package com.ajy.mytownplace.web.dto.auth;

import com.ajy.mytownplace.domain.user.User;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class SignupDto {
    @NotBlank
    String name;
    @NotBlank
    @Size(min = 2, max = 15)
    String username;
    @NotBlank
    String email;
    @NotBlank
    String password;
    @NotBlank
    String location;

    public User toEntity(){
        return User.builder()
                .username(username)
                .password(password)
                .email(email)
                .name(name)
                .location(location)
                .build();
    }
}
