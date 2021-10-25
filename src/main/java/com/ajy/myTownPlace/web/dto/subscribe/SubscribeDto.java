package com.ajy.myTownPlace.web.dto.subscribe;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class SubscribeDto {
    private int id;
    private String username;
    private String profileImageUrl;
    private String name;
    private BigInteger subscribeState;//로그인된 유저가 구독한 유저인지? 이 여부가 버튼모양을 결정
    private BigInteger equalUserState;//로그인 된 유저와 구독된 유저가 같은지? 같다면 버튼 안 보이게.
}
