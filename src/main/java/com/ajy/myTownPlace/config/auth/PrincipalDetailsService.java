package com.ajy.myTownPlace.config.auth;

import com.ajy.myTownPlace.domain.user.User;
import com.ajy.myTownPlace.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service//UserDetailsService를 PrincipalDetailsService로 바꿔치기
public class PrincipalDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    //1. 패스워드는 알아서 체킹하니까 신결 쓸 필요 없다.
    //2. 리턴이 잘 되면 자동으로 UserDetails 타입을 세션으로 만든다.
    @Override
    public UserDetails loadUserByUsername(String user) throws UsernameNotFoundException {
        User userEntity = null;
        if (!user.contains("@"))
            userEntity = userRepository.findByUsername(user);
        else
            userEntity = userRepository.findByEmail(user);

        if (userEntity == null) {
            return null;
        } else {
            return new PrincipalDetails(userEntity);
        }
    }
}
