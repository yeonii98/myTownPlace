package com.ajy.myTownPlace.naver.controller;

import com.ajy.myTownPlace.naver.dto.NaverClient;
import com.ajy.myTownPlace.naver.dto.NaverSearchImageReq;
import com.ajy.myTownPlace.naver.dto.NaverSearchImageRes;
import com.ajy.myTownPlace.naver.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class NaverClientController {
    private final NaverClient naverClinet;

//    @GetMapping("/naverTest")
//    public NaverSearchLocalRes test(){
//        NaverSearchLocalReq search = new NaverSearchLocalReq();
//        search.setQuery("풍무동" + "맛집");
//
//        NaverSearchLocalRes result = naverClinet.localSearch(search);
//        System.out.println(result);
//        return result;
//    }

    @GetMapping("/naverTest2")
    public NaverSearchImageRes test2(){
        NaverSearchImageReq search = new NaverSearchImageReq();
        search.setQuery("강릉해변막국수");

        NaverSearchImageRes result = naverClinet.searchImage(search);
        System.out.println(result);
        return result;
    }
}
