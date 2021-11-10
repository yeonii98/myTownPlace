package com.ajy.myTownPlace.web.dto.user;

import com.ajy.myTownPlace.domain.user.User;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class UserPwdUpdateDto {
    @NotBlank
    private String password;
    @NotBlank
    private String newPassword;
    @NotBlank
    private String newPasswordConfirm;

}
