package com.ajy.myTownPlace.config.oauth;

import com.ajy.myTownPlace.config.auth.PrincipalDetails;
import com.ajy.myTownPlace.config.oauth.provider.GoogleUserInfo;
import com.ajy.myTownPlace.config.oauth.provider.NaverUserInfo;
import com.ajy.myTownPlace.config.oauth.provider.OAuth2UserInfo;
import com.ajy.myTownPlace.domain.user.User;
import com.ajy.myTownPlace.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class PrincipalOauth2UserService extends DefaultOAuth2UserService {

    private final UserRepository userRepository;

    //구글로 부터 받은 userRequest(사용자 정보) 데이터에 대한 후처리되는 함수
    //해당 함수 종료시 @AuthentocationPrincipal 어노테이션이 만들어짐
   @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
       OAuth2User oAuth2User = super.loadUser(userRequest);

       OAuth2UserInfo oAuth2UserInfo = null;
       if(userRequest.getClientRegistration().getRegistrationId().equals("google")){
           oAuth2UserInfo = new GoogleUserInfo(oAuth2User.getAttributes());
       }else if(userRequest.getClientRegistration().getRegistrationId().equals("naver")){
           oAuth2UserInfo = new NaverUserInfo((Map)oAuth2User.getAttributes().get("response"));
       }

       String provider = oAuth2UserInfo.getProvider(); //google
       String providerId = oAuth2UserInfo.getProviderId();
       String email = oAuth2UserInfo.getEmail();
       int idx = email.indexOf('@');
       String username = email.substring(0, idx);
       String password = UUID.randomUUID() + providerId;
       String name = oAuth2UserInfo.getName();
       String location = "서울역";
       String role = "ROLE_USER";

       User userEntity = userRepository.findByUsername(username);

       if(userEntity == null){
           userEntity = User.builder()
                   .username(username)
                   .name(name)
                   .email(email)
                   .password(password)
                   .role(role)
                   .location(location)
                   .provider(provider)
                   .providerId(providerId)
                   .build();
           userRepository.save(userEntity);
       }

       return new PrincipalDetails(userEntity, oAuth2User.getAttributes());
    }
}
