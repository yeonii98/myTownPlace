package com.cos.photogramstart.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity //해당 파일로 시큐리티를 활성화
@Configuration //IoC
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        super.configure(http); // 이거를 비활성화 하면 기존 시큐리티가 가지고 있는 기능이 다 비활성화 됨
        http.csrf().disable();//csrf 비활성화, 로그인 페이지에서 하든, 포스트맨에서 하든 상관없어짐
        http.authorizeRequests()
                .antMatchers("/","/user/**","/image/**","/subscribe/**","/comment/**").authenticated()//인증이 필요한 페이지
                .anyRequest().permitAll()//그 외 페이지는 상관 없음
                .and()
                .formLogin()
                .loginPage("/auth/signin")//인증이 필요한 페이지를 요청하면 로그인 페이지로 이동
                .defaultSuccessUrl("/");//정상 로그인 시 이동

    }
}
