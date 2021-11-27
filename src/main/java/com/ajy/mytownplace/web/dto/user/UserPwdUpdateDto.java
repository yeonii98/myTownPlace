package com.ajy.mytownplace.web.dto.user;

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
