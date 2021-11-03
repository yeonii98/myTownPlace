package com.ajy.myTownPlace.config.oauth.provider;

import java.util.Map;

public class KakaoUserInfo implements OAuth2UserInfo {

    private Map<String, Object> attributes;

    public KakaoUserInfo(Map<String, Object> attributes){
        this.attributes = attributes;
    }

    @Override
    public String getProviderId() {
        int id = (int)attributes.get("id");
        return String.valueOf(id);
    }

    @Override
    public String getProvider() {
        return "kakao";
    }

    @Override
    public String getEmail() {
        return (String) ((Map)attributes.get("kakao_account")).get("email");
    }

    @Override
    public String getName() {
        return (String) ((Map)((Map)attributes.get("kakao_account")).get("profile")).get("nickname");
    }
}
