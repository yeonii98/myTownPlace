package com.ajy.mytownplace.web;

import com.ajy.mytownplace.config.auth.PrincipalDetails;
import com.ajy.mytownplace.service.UserService;
import com.ajy.mytownplace.web.dto.user.UserProfileDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RequiredArgsConstructor
@Controller
public class UserController {

    private final UserService userService;

    @GetMapping("/user/{pageUserId}")
    public String profile(@PathVariable int pageUserId, Model model, @AuthenticationPrincipal PrincipalDetails principalDetails){
        UserProfileDto dto = userService.profileUser(1, principalDetails.getUser().getId());
        model.addAttribute("dto", dto);
        return "user/profile";
    }

    @GetMapping("/user/update")
    public String update(){
        return "user/update";
    }

    @GetMapping("user/pwdUpdate")
    public String pwdUpdate(){
        return "user/pwdUpdate";
    }
}
