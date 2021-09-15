package com.cos.photogramstart.web.dto.user;

import com.cos.photogramstart.domain.user.User;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class UserUpdateDto {
    @NotBlank
    private String name;
    @NotBlank
    private String username;
    private String website;
    private String bio;
    private String phone;
    private String gender;
    private String location;

    //조금 위험함. 코드 수정이 필요할 예정
    public User toEntity(){
        return User.builder()
                .username(username)
                .name(name)
                .website(website)
                .bio(bio)
                .phone(phone)
                .gender(gender)
                .location(location)
                .build();
    }
}
