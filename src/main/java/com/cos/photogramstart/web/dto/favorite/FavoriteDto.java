package com.cos.photogramstart.web.dto.favorite;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class FavoriteDto {
    private String toPlaceId;
    private String name;
    private String detailUrl;
    private String img;
    private BigInteger favoriteState;//로그인 된 유저가 즐겨찾기에 추가한 음식점인지!
}
