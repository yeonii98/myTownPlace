package com.cos.photogramstart.web.dto.review;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class StoryDto {
    private String name;
    private String category;
    private String address;
    private String phone;
    private String detailUrl;
    private String image;
}
