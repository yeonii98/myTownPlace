package com.cos.photogramstart.web.dto.story;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class StoryDto {
    private String id;
    private String name;
    private String category;
    private String address;
    private String phone;
    private String detailUrl;
    private String image;
    private Boolean favoriteState;
    private int favoriteCnt;
}
