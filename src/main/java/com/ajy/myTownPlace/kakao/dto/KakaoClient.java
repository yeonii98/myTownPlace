package com.ajy.myTownPlace.kakao.dto;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Component
public class KakaoClient {

    @Value("${kakao.client.id}")
    private String kakaoClientId;

    @Value("${kakao.url.search.local}")
    private String kakaoLocalSearchUrl;

    public KakaoSearchLocalRes localSearch(KakaoSearchLocalReq kakaoSearchLocalReq) {
        var uri = UriComponentsBuilder
                .fromUriString(kakaoLocalSearchUrl)
                .queryParams(kakaoSearchLocalReq.toMultiValueMap())
                .build()
                .encode()
                .toUri();

        var headers = new HttpHeaders();
        headers.set("Authorization", "KakaoAK " + kakaoClientId);
        headers.setContentType(MediaType.APPLICATION_JSON);

        var httpEntity = new HttpEntity<>(headers);
        var responseType = new ParameterizedTypeReference<KakaoSearchLocalRes>(){};


        var responseEntity = new RestTemplate()
                .exchange(
                        uri,
                        HttpMethod.GET,
                        httpEntity,
                        responseType
                );

        return responseEntity.getBody();
    }
}
