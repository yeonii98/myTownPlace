package com.cos.photogramstart.kakao.controller;

import com.cos.photogramstart.kakao.dto.KakaoClient;
import com.cos.photogramstart.kakao.dto.KakaoSearchLocalReq;
import com.cos.photogramstart.kakao.dto.KakaoSearchLocalRes;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class KakaoClientController {
    private final KakaoClient kakaoClinet;

    @GetMapping("/kakaoTest")
    public KakaoSearchLocalRes test(){
        KakaoSearchLocalReq search = new KakaoSearchLocalReq();
        search.setPage(2);
        search.setQuery("풍무동 맛집");

        KakaoSearchLocalRes result = kakaoClinet.localSearch(search);
        System.out.println(result);
        return result;
    }
}
