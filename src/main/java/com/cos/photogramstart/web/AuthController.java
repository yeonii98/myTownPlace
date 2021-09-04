package com.cos.photogramstart.web;

import com.cos.photogramstart.domain.user.User;
import com.cos.photogramstart.service.AuthService;
import com.cos.photogramstart.web.dto.auth.SignupDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import lombok.extern.log4j.Log4j;
import lombok.extern.log4j.Log4j2;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.function.Supplier;
import java.util.logging.Logger;

@RequiredArgsConstructor //final 필드를 DI 할때 사용
@Log4j
@Controller // 1.IoC, 2.파일을 리턴하는 컨트롤러
public class AuthController {

    private final AuthService authService;

//    public AuthController(AuthService authService){
//        this.authService = authService;
//    }

    @GetMapping("/auth/signup")
    public String signupForm() {
        return "auth/signup";
    }

    @GetMapping("/auth/signin")
    public String signinForm() {
        return "auth/signin";
    }

    //회원 가입 버튼 -> /auth/signup -> /auth/signin
    @PostMapping("/auth/signup")
    public String signup(SignupDto signupDto) {//폼으로 데이터가 날아오면 key=value (x-www-form-urlencoded) 형식으로 받아옴
        log.info(signupDto.toString());
        User user = signupDto.toEntity();
        log.info(user.toString());
        User userEntity = authService.join(user);
        log.info(userEntity);
        return "auth/signin";
    }


}
