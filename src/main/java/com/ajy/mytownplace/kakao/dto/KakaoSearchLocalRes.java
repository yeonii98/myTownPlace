package com.ajy.mytownplace.kakao.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class KakaoSearchLocalRes {

    private List<Documents> documents; // XML 포멧에서는 item 태그로, JSON 포멧에서는 items 속성으로 표현된다. 개별 검색 결과이며 title, link, description, address, mapx, mapy를 포함한다.

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Documents {
        private String address_name;
        private String category_group_code;
        private String category_group_name;
        private String category_name;
        private String distance;
        private String id;
        private String phone;
        private String place_name;
        private String place_url;
        private String road_address_name;
        private String x;
        private String y;
    }

    private Meta meta;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Meta{
        private boolean is_end;
        private int pageable_count;
        private Same_Name same_name;

        @Data
        @NoArgsConstructor
        @AllArgsConstructor
        private static class Same_Name{
            private String keyword;
            private List<String> region;
            private String selected_region;
        }
        private int total_count;
    }
}